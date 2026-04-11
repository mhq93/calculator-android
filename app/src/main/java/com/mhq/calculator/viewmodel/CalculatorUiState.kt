package com.mhq.calculator.viewmodel

data class CalculatorUiState (
    val calculatorExpression: String = "",
    val calculatorResult: String = "",
    var isOperatorLocked: Boolean = false
)