import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Main application UI component.
 * Provides three main features:
 * 1. Mathematical expression evaluation
 * 2. Proof verification
 * 3. Word problem solving
 * 
 * Supports both offline (local) and online (API-based) solving modes for agentic behavior.
 */
@Composable
fun App() {
    MaterialTheme {
        var expression by remember { mutableStateOf("Integrate[Sin[x], x]") }
        var result by remember { mutableStateOf("") }
        var proofRequest by remember { mutableStateOf("That the empty set is a subset of all sets") }
        var proofResult by remember { mutableStateOf("") }
        var wordProblem by remember { mutableStateOf("If I have 2 apples and I buy 3 more, how many apples do I have?") }
        var wordProblemResult by remember { mutableStateOf("") }
        var useOnlineMode by remember { mutableStateOf(false) }
        var apiEndpoint by remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Online Mode (API): ")
                Switch(
                    checked = useOnlineMode,
                    onCheckedChange = { useOnlineMode = it }
                )
            }
            
            if (useOnlineMode) {
                TextField(
                    value = apiEndpoint,
                    onValueChange = { apiEndpoint = it },
                    label = { Text("API Endpoint (optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            TextField(
                value = expression,
                onValueChange = { expression = it },
                label = { Text("Enter a math expression") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        result = if (useOnlineMode && isNetworkAvailable()) {
                            val config = SolverConfig(SolverMode.ONLINE, apiEndpoint, "")
                            solveOnline(expression, config)
                        } else {
                            evaluateExpression(expression)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (useOnlineMode) "Solve Online" else "Solve Offline")
            }
            Text(result)

            TextField(
                value = proofRequest,
                onValueChange = { proofRequest = it },
                label = { Text("Enter a proof request") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        proofResult = if (useOnlineMode && isNetworkAvailable()) {
                            val config = SolverConfig(SolverMode.ONLINE, apiEndpoint, "")
                            solveOnline("Prove: $proofRequest", config)
                        } else {
                            proveExpression(proofRequest)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (useOnlineMode) "Prove Online" else "Prove Offline")
            }
            Text(proofResult)

            TextField(
                value = wordProblem,
                onValueChange = { wordProblem = it },
                label = { Text("Enter a word problem") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        wordProblemResult = if (useOnlineMode && isNetworkAvailable()) {
                            val config = SolverConfig(SolverMode.ONLINE, apiEndpoint, "")
                            solveOnline(wordProblem, config)
                        } else {
                            solveWordProblem(wordProblem)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (useOnlineMode) "Solve Online" else "Solve Offline")
            }
            Text(wordProblemResult)
        }
    }
}

// Platform-specific implementations of mathematical operations

/**
 * Evaluates a mathematical expression.
 * Platform-specific behavior:
 * - Desktop: Full symbolic computation using Symja/MathEclipse
 * - Android: Basic arithmetic using exp4j
 * - iOS: Basic arithmetic operations
 */
expect fun evaluateExpression(expression: String): String

/**
 * Verifies or generates mathematical proofs.
 * Currently only supported on Desktop platform using Symja/MathEclipse.
 */
expect fun proveExpression(expression: String): String

/**
 * Solves word problems using natural language processing.
 * Platform-specific capabilities:
 * - Desktop: Advanced NLP with calculus support
 * - Android: Basic arithmetic word problems
 * - iOS: Basic arithmetic word problems
 */
expect fun solveWordProblem(problem: String): String

expect fun getPlatformName(): String