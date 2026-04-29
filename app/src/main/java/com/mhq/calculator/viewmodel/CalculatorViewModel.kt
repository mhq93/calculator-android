package com.mhq.calculator.viewmodel

import androidx.lifecycle.ViewModel
import com.mhq.calculator.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState = _uiState.asStateFlow()

    val operatorIds = listOf(
        R.string.btn_add,
        R.string.btn_subtract,
        R.string.btn_multiply,
        R.string.btn_divide
    )

    fun onButtonClick(id: Int) {
        when(id) {
            R.string.btn_parentheses -> handleParentheses()
            R.string.btn_clear -> clearDisplay()
            R.string.btn_equals -> evaluateExpression()
            R.string.btn_percentage -> calculatePercentage()
            R.string.btn_back -> handleBackspace()
            else -> appendToExpression(id)
        }
    }

    fun sanitizeExpression(expression: String): String {
        return expression
            .replace("×", "*")
            .replace("÷", "/")
            .replace("−", "-")
    }

    fun evaluateExpression() {
        val expression = uiState.value.calculatorExpression
        if (expression.isEmpty()) return

        try {
            var sanitized = sanitizeExpression(expression)

            // Auto-close parentheses to prevent exp4j build errors
            val openCount = sanitized.count { it == '(' }
            val closeCount = sanitized.count { it == ')' }
            if (openCount > closeCount) {
                sanitized += ")".repeat(openCount - closeCount)
            }

            val result = ExpressionBuilder(sanitized)
                .build()
                .evaluate()

            _uiState.value = uiState.value.copy(
                calculatorResult = formatResult(result)
            )
        } catch (e: ArithmeticException) {
            // This catches Division by Zero
            _uiState.value = uiState.value.copy(
                calculatorResult = "Error: Div by 0"
            )
        } catch (e: Exception) {
            _uiState.value = uiState.value.copy(
                calculatorResult = "Error"
            )
        }
    }

    private fun formatResult(result: Double): String {
        return if (result % 1.0 == 0.0) {
            result.toLong().toString() // Show "5" instead of "5.0"
        } else {
            result.toString()
        }
    }

    fun appendToExpression(id: Int) {
        val isOperator = id in operatorIds
        val addedText = mapToButtonText(id)
        val currentNumber = uiState.value.calculatorExpression
            .split("+", "−", "×", "÷")
            .last()

        //handle decimal button...
        if (id == R.string.btn_decimal && currentNumber.contains(".")) return

        //handle adding a zero only if followed by a decimal or an operator...
        if (currentNumber == "0" && id != R.string.btn_decimal && id !in operatorIds) return

        //handle start with an operator...
        if (uiState.value.calculatorExpression.isEmpty() && isOperator) return

        if (isOperator && uiState.value.isOperatorLocked) {
            _uiState.value = uiState.value.copy(
                calculatorExpression = uiState.value.calculatorExpression.dropLast(1) + addedText,
                isOperatorLocked = true
            )
        } else {
            _uiState.value = uiState.value.copy(
                calculatorExpression = uiState.value.calculatorExpression + addedText,
                isOperatorLocked = isOperator
            )
        }
    }

    fun mapToButtonText(btnLabel: Int): String {
        return when(btnLabel) {
            R.string.btn_0 -> "0"
            R.string.btn_1 -> "1"
            R.string.btn_2 -> "2"
            R.string.btn_3 -> "3"
            R.string.btn_4 -> "4"
            R.string.btn_5 -> "5"
            R.string.btn_6 -> "6"
            R.string.btn_7 -> "7"
            R.string.btn_8 -> "8"
            R.string.btn_9 -> "9"
            R.string.btn_add -> "+"
            R.string.btn_subtract -> "−"
            R.string.btn_multiply -> "×"
            R.string.btn_divide -> "÷"
            R.string.btn_decimal -> "."
            R.string.btn_equals -> "="
            R.string.btn_percentage -> "%"
            R.string.btn_parentheses -> "( )"
            R.string.btn_clear -> "AC"
            else -> "Not Found"
        }
    }

    fun clearDisplay(){
        _uiState.value = uiState.value.copy(
            calculatorExpression = "",
            calculatorResult = ""
        )
    }

    fun handleBackspace() {
        val currentExpression = uiState.value.calculatorExpression
        if (currentExpression.isNotEmpty()) {
            val newExpression = currentExpression.dropLast(1)

            // Update state and re-check if the new last character is an operator
            _uiState.value = uiState.value.copy(
                calculatorExpression = newExpression,
                isOperatorLocked = if (newExpression.isNotEmpty()) {
                    val lastChar = newExpression.last().toString()
                    // Check against your localized operator symbols
                    lastChar in listOf("+", "−", "×", "÷")
                } else {
                    false
                }
            )
        }
    }

    fun calculatePercentage() {
        val currentExpression = uiState.value.calculatorExpression
        if (currentExpression.isEmpty()) return

        val numberRegex = Regex("""(\d+\.?\d*)$""")
        val match = numberRegex.find(currentExpression) ?: return

        val lastNumberValue = match.value.toDoubleOrNull() ?: return

        // Find the first operand
        val firstOperandRegex = Regex("""^(\d+\.?\d*)""")
        val firstMatch = firstOperandRegex.find(currentExpression)
        val firstOperandValue = firstMatch?.value?.toDoubleOrNull()

        val percentageValue = if (firstOperandValue != null && firstOperandValue != lastNumberValue) {
            // 20% of 50
            firstOperandValue * lastNumberValue / 100.0
        } else {
            // Standalone: 50% → 0.5
            lastNumberValue / 100.0
        }

        val formattedValue = percentageValue.toBigDecimal()
            .stripTrailingZeros()
            .toPlainString()

        val newExpression = currentExpression.substring(0, match.range.first) + formattedValue

        _uiState.value = uiState.value.copy(
            calculatorExpression = newExpression,
            isOperatorLocked = false
        )
    }

    private fun handleParentheses() {
        val currentExpression = uiState.value.calculatorExpression

        // 1. If empty, just start an open parenthesis
        if (currentExpression.isEmpty()) {
            updateExpression("(")
            return
        }

        val lastChar = currentExpression.last()
        val openCount = currentExpression.count { it == '(' }
        val closeCount = currentExpression.count { it == ')' }

        // 2. Logic to decide between '(' and ')'
        val nextChar = when {
            // If it ends with an operator or another open paren, we must open another
            lastChar in listOf('+', '−', '×', '÷', '(') -> "("

            // If we have unclosed parentheses AND the last char is a number or a closed paren, we close it
            openCount > closeCount && (lastChar.isDigit() || lastChar == ')') -> ")"

            // If the last char is a digit but no parens are open, treat it as implicit multiplication "5(" -> "5×("
            lastChar.isDigit() || lastChar == ')' -> "×("

            else -> "("
        }

        updateExpression(currentExpression + nextChar)
    }

    // Helper to keep code DRY and handle the StateFlow update
    private fun updateExpression(newExpression: String) {
        _uiState.value = uiState.value.copy(
            calculatorExpression = newExpression,
            // Reset operator lock since we just added a parenthesis
            isOperatorLocked = false
        )
    }
}
