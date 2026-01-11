import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission

private var appContext: Context? = null

fun initializeContext(context: Context) {
    appContext = context
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
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
    return try {
        // Placeholder for actual HTTP API call
        "Online solving requires API implementation. Please configure API endpoint and key.\n" +
        "Problem detected: ${classifyProblem(problem)}\n" +
        "Falling back to offline mode: ${evaluateExpression(problem)}"
    } catch (e: Exception) {
        "Online solve error: ${e.message}. Using offline mode."
    }
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
actual fun isNetworkAvailable(): Boolean {
    val context = appContext ?: return false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val network = connectivityManager?.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
