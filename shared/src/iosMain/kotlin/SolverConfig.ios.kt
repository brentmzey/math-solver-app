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
        "Falling back to offline mode: ${solveWordProblem(problem)}"
    } catch (e: Exception) {
        "Online solve error: ${e.message}. Using offline mode."
    }
}

actual fun isNetworkAvailable(): Boolean {
    // iOS network detection would require platform-specific APIs
    // For now, return false as a safe default
    return false
}
