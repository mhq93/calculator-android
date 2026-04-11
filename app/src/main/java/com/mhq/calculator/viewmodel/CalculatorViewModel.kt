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
            R.string.btn_sign -> invertSign()
            R.string.btn_clear -> clearDisplay()
            R.string.btn_equals -> evaluateExpression()
            R.string.btn_percentage -> calculatePercentage()
            else -> appendToExpression(id)
        }
    }

    fun sanitizeExpression(expression: String): String {
        return expression
            .replace("×", "*")
            .replace("÷", "/")
            .replace("−", "-")
    }

    fun evaluateExpression(){
        try {
            val result = ExpressionBuilder(
                sanitizeExpression(
                    uiState.value.calculatorExpression
                )
            )
                .build()
                .evaluate()
            _uiState.value = uiState.value.copy(
                calculatorResult = result.toString()
            )
        } catch (e: Exception) {
            _uiState.value = uiState.value.copy(
                calculatorResult = ""
            )
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
            R.string.btn_sign -> "+/-"
            R.string.btn_clear -> "C"
            else -> "Not Found"
        }
    }

    fun clearDisplay(){
        _uiState.value = uiState.value.copy(
            calculatorExpression = "",
            calculatorResult = ""
        )
    }

    fun calculatePercentage(){}
    fun invertSign(){}
}
