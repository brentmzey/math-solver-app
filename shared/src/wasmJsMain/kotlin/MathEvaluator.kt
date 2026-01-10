actual fun evaluateExpression(expression: String): String {
    return try {
        val numbers = Regex("\\d+(\\.\\d+)?").findAll(expression).map { it.value.toDouble() }.toList()
        
        when {
            expression.contains("+") -> {
                val result = numbers.sum()
                result.toString()
            }
            expression.contains("-") && numbers.size >= 2 -> {
                val result = numbers[0] - numbers.drop(1).sum()
                result.toString()
            }
            expression.contains("*") -> {
                val result = numbers.fold(1.0) { acc, n -> acc * n }
                result.toString()
            }
            expression.contains("/") && numbers.size >= 2 && numbers[1] != 0.0 -> {
                val result = numbers[0] / numbers[1]
                result.toString()
            }
            else -> "Error: Only basic arithmetic operations are supported for web"
        }
    } catch (e: Exception) {
        "Error: ${e.message ?: "Invalid expression"}"
    }
}

actual fun proveExpression(expression: String): String {
    return "Proof solving is not supported on web platform."
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

actual fun getPlatformName(): String = "Web"
