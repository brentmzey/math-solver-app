actual fun evaluateExpression(expression: String): String {
    return "Not supported on this platform"
}

actual fun proveExpression(expression: String): String {
    return "Proof solving is not supported on this platform."
}

actual fun solveWordProblem(problem: String): String {
    return try {
        val numbers = Regex("\\d+").findAll(problem).map { it.value.toDouble() }.toList()
        
        when {
            problem.contains("sum", ignoreCase = true) || problem.contains("total", ignoreCase = true) || 
            problem.contains("add", ignoreCase = true) || problem.contains("plus", ignoreCase = true) ||
            problem.contains("more", ignoreCase = true) -> {
                val result = numbers.sum()
                "Solution: $result"
            }
            problem.contains("difference", ignoreCase = true) || problem.contains("subtract", ignoreCase = true) ||
            problem.contains("less", ignoreCase = true) -> {
                val result = if (numbers.size >= 2) numbers[0] - numbers.drop(1).sum() else 0.0
                "Solution: $result"
            }
            problem.contains("product", ignoreCase = true) || problem.contains("multiply", ignoreCase = true) ||
            problem.contains("times", ignoreCase = true) -> {
                val result = numbers.fold(1.0) { acc, n -> acc * n }
                "Solution: $result"
            }
            problem.contains("quotient", ignoreCase = true) || problem.contains("divide", ignoreCase = true) -> {
                val result = if (numbers.size >= 2 && numbers[1] != 0.0) numbers[0] / numbers[1] else 0.0
                "Solution: $result"
            }
            else -> {
                val result = numbers.sum()
                "Solution: $result"
            }
        }
    } catch (e: Exception) {
        "Error solving word problem: ${e.message ?: "An error occurred"}"
    }
}
