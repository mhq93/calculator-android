package com.mhq.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mhq.calculator.ui.screens.CalculatorScreen
import com.mhq.calculator.ui.theme.CalculatorTheme
import com.mhq.calculator.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorScreen(calculatorViewModel = viewModel)
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(calculatorViewModel = CalculatorViewModel())
}