package com.mhq.calculator.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhq.calculator.ui.theme.ColorDigit

@Composable
fun CalculatorDisplay(
    expression: String,
    result: String,
    modifier: Modifier = Modifier
){
    val cursorAlpha = remember { Animatable(1f) }
    val scrollState = rememberScrollState()

    LaunchedEffect(expression) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    LaunchedEffect(Unit) {
            while(true) {
                cursorAlpha.animateTo(0f, animationSpec = tween(500))
                cursorAlpha.animateTo(1f, animationSpec = tween(500))
            }
    }

    val blinkingExpression = buildAnnotatedString {
        withStyle(SpanStyle(color = ColorDigit)) {
            append(expression)
        }
        withStyle(SpanStyle(color = ColorDigit.copy(alpha = cursorAlpha.value))) {
            append("|")
        }
    }

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = blinkingExpression,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Clip,
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ColorDigit,
            modifier = Modifier
                .horizontalScroll(scrollState)
                .height(48.dp)
        )
        Spacer(
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = result,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Clip,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = ColorDigit,
            modifier = Modifier
                .height(48.dp)
        )
    }
}

@Preview(showBackground = false)
@Composable
fun CalculatorDisplayPreview() {
    CalculatorDisplay(expression = "", result = "")
}