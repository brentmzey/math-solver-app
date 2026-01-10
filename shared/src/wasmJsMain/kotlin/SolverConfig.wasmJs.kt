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
        // Placeholder for actual HTTP API call using Fetch API
        "Online solving requires API implementation. Please configure API endpoint and key.\n" +
        "Problem detected: ${classifyProblem(problem)}\n" +
        "Falling back to offline mode: ${evaluateExpression(problem)}"
    } catch (e: Exception) {
        "Online solve error: ${e.message}. Using offline mode."
    }
}

actual fun isNetworkAvailable(): Boolean {
    // Web always has network by definition
    return true
}
