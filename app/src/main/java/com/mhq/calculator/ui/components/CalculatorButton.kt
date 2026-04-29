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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhq.calculator.ui.theme.CalculatorTheme
import com.mhq.calculator.ui.theme.ColorButtonText
import com.mhq.calculator.ui.theme.ColorUtility

@Composable
fun CalculatorButton(
    id: Int,
    label: String,
    backgroundColor: Color,
    modifier: Modifier,
    onClick: (Int) -> Unit = {}
) {

    val haptic = LocalHapticFeedback.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(25))
            .background(backgroundColor)
            .clickable{
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onClick(id)
            }
    ) {
        Text(
            text = label,
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold,
            color = ColorButtonText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview() {
    CalculatorTheme {
        CalculatorButton(
            id = 0,
            label = "0",
            backgroundColor = ColorUtility,
            modifier = Modifier.size(80.dp)
            )
    }
}