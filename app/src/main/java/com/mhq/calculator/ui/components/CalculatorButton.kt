package com.mhq.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhq.calculator.ui.theme.CalculatorTheme
import com.mhq.calculator.ui.theme.ColorBackground

@Composable
fun CalculatorButton(
    label: String,
    backgroundColor: Color,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(25))
            .background(backgroundColor)
            .clickable(onClick = {})
    ) {
        Text(
            text = label,
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview() {
    CalculatorTheme {
        CalculatorButton(
            label = "0",
            backgroundColor = ColorBackground,
            modifier = Modifier.size(80.dp)
            )
    }
}