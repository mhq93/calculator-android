package com.mhq.calculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhq.calculator.R
import com.mhq.calculator.ui.theme.CalculatorTheme
import com.mhq.calculator.ui.theme.ColorDigit
import com.mhq.calculator.ui.theme.ColorOperator
import com.mhq.calculator.ui.theme.ColorUtility


@Composable
fun CalculatorGrid(onButtonClick: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CalculatorButton(
                id = R.string.btn_clear,
                label = stringResource(R.string.btn_clear),
                backgroundColor = ColorUtility,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_sign,
                label = stringResource(R.string.btn_sign),
                backgroundColor = ColorUtility,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_percentage,
                label = stringResource(R.string.btn_percentage),
                backgroundColor = ColorUtility,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_divide,
                label = stringResource(R.string.btn_divide),
                backgroundColor = ColorOperator,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CalculatorButton(
                id = R.string.btn_7,
                label = stringResource(R.string.btn_7),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_8,
                label = stringResource(R.string.btn_8),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_9,
                label = stringResource(R.string.btn_9),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_multiply,
                label = stringResource(R.string.btn_multiply),
                backgroundColor = ColorOperator,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CalculatorButton(
                id = R.string.btn_4,
                label = stringResource(R.string.btn_4),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_5,
                label = stringResource(R.string.btn_5),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_6,
                label = stringResource(R.string.btn_6),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_subtract,
                label = stringResource(R.string.btn_subtract),
                backgroundColor = ColorOperator,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CalculatorButton(
                id = R.string.btn_1,
                label = stringResource(R.string.btn_1),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_2,
                label = stringResource(R.string.btn_2),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_3,
                label = stringResource(R.string.btn_3),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_add,
                label = stringResource(R.string.btn_add),
                backgroundColor = ColorOperator,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CalculatorButton(
                id = R.string.btn_0,
                label = stringResource(R.string.btn_0),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(2f)
                    .aspectRatio(2f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_decimal,
                label = stringResource(R.string.btn_decimal),
                backgroundColor = ColorDigit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
            CalculatorButton(
                id = R.string.btn_equals,
                label = stringResource(R.string.btn_equals),
                backgroundColor = ColorOperator,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp),
                onClick = { id ->
                    onButtonClick(id)
                }
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun CalculatorGridPreview() {
    CalculatorTheme {
        CalculatorGrid {}
    }
}