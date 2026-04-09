package com.mhq.calculator.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorDisplay(
    expression: String,
    result: String,
    modifier: Modifier = Modifier
){

    val cursorAlpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            cursorAlpha.animateTo(0f, animationSpec = tween(500))
            cursorAlpha.animateTo(1f, animationSpec = tween(500))        }
    }

    val blinkingExpression = buildAnnotatedString {
        withStyle(
            SpanStyle(color = Color.Gray)
        ) {
            append(result)
        }
        withStyle(
            SpanStyle(color = Color.Gray.copy(alpha = cursorAlpha.value))
        ) {
            append("|")
        }
    }

    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            )
    ) {
        Text(
            text = blinkingExpression,
            maxLines = 1,
            fontSize = 64.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Gray
        )
        Spacer(
            modifier = modifier
                .padding(24.dp)
        )
        Text(
            text = result,
            maxLines = 1,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = false)
@Composable
fun CalculatorDisplayPreview() {
    CalculatorDisplay(expression = "1000", result = "100")
}