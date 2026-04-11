package com.mhq.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhq.calculator.R
import com.mhq.calculator.ui.components.CalculatorDisplay
import com.mhq.calculator.ui.components.CalculatorGrid
import com.mhq.calculator.ui.theme.ColorAppBackground
import com.mhq.calculator.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(calculatorViewModel: CalculatorViewModel) {

    val calculatorUiState by calculatorViewModel.uiState.collectAsState()
    val expression = calculatorUiState.calculatorExpression
    val result = calculatorUiState.calculatorResult

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorAppBackground)
    ) {
        Spacer(
            modifier = Modifier
                .padding(48.dp)
        )
        CalculatorDisplay(expression, result)
        CalculatorGrid(onButtonClick = { id -> calculatorViewModel.onButtonClick(id) })
    }
}

@Preview(showBackground = false)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(calculatorViewModel = CalculatorViewModel())
}