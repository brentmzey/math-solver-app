import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

actual fun detectBestSolverMode(problemComplexity: ProblemComplexity): SolverMode {
    return if (isNetworkAvailable() && problemComplexity in listOf(
        ProblemComplexity.DISCRETE_MATH,
        ProblemComplexity.REAL_ANALYSIS,
        ProblemComplexity.SYMBOLIC_COMPUTATION
    )) {
        SolverMode.ONLINE
    } else {
        SolverMode.OFFLINE
    }
}

actual suspend fun solveOnline(problem: String, config: SolverConfig): String {
    val client = createHttpClient()

    val request = OpenAiRequest(
        model = "gpt-3.5-turbo",
        messages = listOf(Message("user", "Solve the following math problem: $problem")),
        maxTokens = 100,
        temperature = 0.7
    )

    return try {
        val response = client.post(config.apiEndpoint.ifEmpty { "https://api.openai.com/v1/chat/completions" }) {
            headers {
                append(HttpHeaders.Authorization, "Bearer ${config.apiKey}")
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
            setBody(Json.encodeToString(request))
        }

        val responseBody = response.bodyAsText()
        val openAiResponse = Json.decodeFromString<OpenAiResponse>(responseBody)
        openAiResponse.choices.firstOrNull()?.message?.content ?: "No solution found"
    } catch (e: Exception) {
        "Error calling API: ${e.message}"
    } finally {
        client.close()
    }
}

actual fun isNetworkAvailable(): Boolean {
    return try {
        val runtime = Runtime.getRuntime()
        val process = runtime.exec("ping -c 1 8.8.8.8")
        process.waitFor() == 0
    } catch (e: Exception) {
        false
    }
}
