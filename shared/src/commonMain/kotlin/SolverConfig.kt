/**
 * Configuration for switching between offline (local) and online (API-based) math solving.
 * This enables agentic behavior by choosing compute mode based on network availability.
 */
data class SolverConfig(
    val mode: SolverMode = SolverMode.OFFLINE,
    val apiEndpoint: String = "",
    val apiKey: String = ""
)

enum class SolverMode {
    OFFLINE,  // Use local models/libraries (Symja, exp4j)
    ONLINE    // Use cloud API (OpenAI, Anthropic, etc.)
}

/**
 * Determines the best solver mode based on network connectivity and problem complexity.
 * This agentic decision-making allows the app to work both offline and online.
 */
expect fun detectBestSolverMode(problemComplexity: ProblemComplexity): SolverMode

enum class ProblemComplexity {
    SIMPLE_ARITHMETIC,      // Basic +,-,*,/
    WORD_PROBLEM,           // Natural language parsing needed
    DISCRETE_MATH,          // Combinatorics, graph theory, logic
    REAL_ANALYSIS,          // Limits, continuity, sequences
    SYMBOLIC_COMPUTATION    // Integration, differentiation, proofs
}

/**
 * Classifies the type of math problem from the input text.
 */
fun classifyProblem(input: String): ProblemComplexity {
    return when {
        // Discrete mathematics keywords
        input.contains(Regex("(permutation|combination|graph|vertex|edge|set theory|logic|propositional|truth table|boolean)", RegexOption.IGNORE_CASE)) -> 
            ProblemComplexity.DISCRETE_MATH
        
        // Real analysis keywords  
        input.contains(Regex("(limit|continuity|convergence|sequence|series|epsilon|delta|supremum|infimum|bounded)", RegexOption.IGNORE_CASE)) -> 
            ProblemComplexity.REAL_ANALYSIS
        
        // Symbolic computation keywords
        input.contains(Regex("(integrate|differentiate|derivative|integral|solve|factor|expand|simplify|D\\[|Integrate\\[)", RegexOption.IGNORE_CASE)) -> 
            ProblemComplexity.SYMBOLIC_COMPUTATION
        
        // Word problem indicators
        input.split(" ").size > 5 && input.contains(Regex("\\d+")) -> 
            ProblemComplexity.WORD_PROBLEM
        
        // Simple arithmetic
        input.matches(Regex("[\\d+\\-*/().\\s]+")) -> 
            ProblemComplexity.SIMPLE_ARITHMETIC
        
        else -> ProblemComplexity.WORD_PROBLEM
    }
}

/**
 * Online solver using external API (requires implementation per platform).
 */
expect suspend fun solveOnline(problem: String, config: SolverConfig): String

/**
 * Check if network is available.
 */
expect fun isNetworkAvailable(): Boolean
