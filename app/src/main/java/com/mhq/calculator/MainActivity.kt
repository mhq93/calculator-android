package com.mhq.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mhq.calculator.ui.theme.CalculatorTheme
import com.mhq.calculator.ui.screens.CalculatorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp(){
    CalculatorScreen()
}


@Preview(showBackground = true)
@Composable
fun CalculatorGridPreview() {
    CalculatorTheme {
        CalculatorApp()
    }
}