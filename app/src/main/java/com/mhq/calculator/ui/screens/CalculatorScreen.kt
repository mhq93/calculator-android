package com.mhq.calculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhq.calculator.ui.components.CalculatorDisplay
import com.mhq.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorScreen() {
   Column(
       verticalArrangement = Arrangement.Top,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier
           .padding(8.dp)
           .fillMaxWidth()
   ) {
       Spacer(
           modifier = Modifier
               .padding(48.dp)
       )
       CalculatorDisplay("", "")
       CalculatorGrid()
   }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}