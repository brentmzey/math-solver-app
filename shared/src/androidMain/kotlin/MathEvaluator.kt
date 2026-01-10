import net.objecthunter.exp4j.ExpressionBuilder

/**
 * Android implementation using exp4j for basic arithmetic evaluation.
 * Supports: +, -, *, /, ^, %, parentheses, and decimal numbers.
 */
actual fun evaluateExpression(expression: String): String {
    return try {
        // Remove spaces to simplify parsing for exp4j
        val cleanExpression = expression.replace(" ", "")

        val supportedOperators = setOf('+', '-', '*', '/', '^', '%')
        val isValidExpression = cleanExpression.any { it in supportedOperators || it.isDigit() || it == '.' || it == '(' || it == ')' }

        if (!isValidExpression) {
            return "Error: Only basic arithmetic operations and numbers are supported for now."
        }

        val expr = ExpressionBuilder(cleanExpression).build()
        val result = expr.evaluate()
        return result.toString()
    } catch (e: Exception) {
        return "Error: ${e.message ?: "Invalid expression"}"
    }
}

/**
 * Android platform does not support proof verification.
 */
actual fun proveExpression(expression: String): String {
    return "Proof solving is not supported on this platform."
}

/**
 * Android implementation of word problem solver.
 * Uses keyword detection and regex pattern matching to solve basic arithmetic word problems.
 * Supports: addition, subtraction, multiplication, division.
 */
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
