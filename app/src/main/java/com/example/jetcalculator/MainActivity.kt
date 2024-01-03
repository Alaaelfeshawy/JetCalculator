package com.example.jetcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetcalculator.component.MyApp
import com.example.jetcalculator.widgets.BillForm
import com.example.jetcalculator.widgets.TopHeader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
               val topHeaderAmount = remember {
                   mutableStateOf("")
               }
                TopHeader(topHeaderAmount)
                MainContent(topHeaderAmount)
            }
        }
    }
}


@Preview
@Composable
fun MainContent(topHeaderAmount : MutableState<String> = mutableStateOf("")) {
    val billAmount = remember{
        mutableStateOf("")
    }

    val validState = remember(billAmount.value) {
        billAmount.value.trim().isNotEmpty() && billAmount.value.toInt() > 0
    }

    val splitNumber = remember {
        mutableIntStateOf(1)
    }

    val sliderPositionState = remember {
        mutableFloatStateOf(0f)
    }
    val tipPercentage = remember {
        mutableIntStateOf(0)
    }

    val tipAmountState = remember {
        mutableDoubleStateOf(0.0)
    }
    BillForm(
        billAmount = billAmount,
        validState = validState,
        splitNumber = splitNumber,
        sliderPositionState = sliderPositionState,
        tipPercentage = tipPercentage,
        tipAmountState = tipAmountState
    ){
        topHeaderAmount.value = it
    }

}
