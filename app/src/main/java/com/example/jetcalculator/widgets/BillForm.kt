package com.example.jetcalculator.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetcalculator.calculateTipAmount
import com.example.jetcalculator.calculateTotalBill
import com.example.jetcalculator.component.InputField


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier,
                     billAmount : MutableState<String>,
                     validState : Boolean,
                     splitNumber : MutableIntState,
                     sliderPositionState : MutableFloatState,
                     tipPercentage : MutableIntState,
                     tipAmountState : MutableDoubleState,
                     onValueChanged : (String)->Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ) ,
        border = BorderStroke(1.dp , color = Color.LightGray)
    ) {
        InputField(
            isEnabled = true,
            isSingleLine = true,
            labeledId = "Enter Bill",
            valueState = billAmount,
            modifier = modifier.fillMaxWidth(),
            onAction = KeyboardActions {
                if (!validState) return@KeyboardActions
                onValueChanged(billAmount.value)
                keyboardController?.hide()
            }
        )

        if (validState){
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "Split" ,
                    modifier = modifier.align(Alignment.CenterVertically))

                Spacer(modifier = Modifier.width(120.dp))
                Row(
                    modifier = modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RoundedButton(imageVector = Icons.Rounded.Remove){
                        if (splitNumber.intValue == 1 ){
                            return@RoundedButton
                        }
                        splitNumber.intValue = splitNumber.intValue.minus(1)
                        onValueChanged(
                            calculateTotalBill(
                            billAmount = billAmount.value.trim().toInt() ,
                            tipAmount = tipAmountState.doubleValue ,
                            splitNumber =  splitNumber.intValue ).toString())
                    }

                    Text(text = splitNumber.intValue.toString() , modifier.padding(3.dp))

                    RoundedButton(imageVector = Icons.Rounded.Add){
                        splitNumber.intValue = splitNumber.intValue.plus(1)
                        onValueChanged(
                            calculateTotalBill(
                            billAmount = billAmount.value.trim().toInt() ,
                            tipAmount = tipAmountState.doubleValue ,
                            splitNumber =  splitNumber.intValue ).toString())

                    }

                }

            }
            Spacer(modifier = modifier.height(12.dp))
            Row(modifier = Modifier.padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.Start,) {
                Text(text = "Tip")
                Spacer(modifier = modifier.width(200.dp))
                Text(text = "$${tipAmountState.doubleValue}",
                    modifier = modifier,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = modifier.height(12.dp))
            Text(text = "${tipPercentage.intValue} %" , modifier = modifier.fillMaxWidth() , textAlign = TextAlign.Center)
            Spacer(modifier = modifier.height(12.dp))
            Slider(
                modifier = modifier.padding(horizontal = 22.dp),
                value = sliderPositionState.floatValue, onValueChange = {
                    sliderPositionState.floatValue = it
                    tipPercentage.intValue =  (sliderPositionState.floatValue.times(100)).toInt()
                    tipAmountState.doubleValue = calculateTipAmount(totalBillAmount = billAmount.value.toDouble() , tipPercentage = tipPercentage.intValue )
                } , steps = 5 , onValueChangeFinished = {
                    onValueChanged(
                        calculateTotalBill(
                        billAmount = billAmount.value.trim().toInt() ,
                        tipAmount = tipAmountState.doubleValue ,
                        splitNumber =  splitNumber.intValue ).toString())
                })

        }else{
            sliderPositionState.floatValue = 0f
            splitNumber.intValue = 1
            tipAmountState.doubleValue = 0.0
            tipPercentage.intValue = 0
            onValueChanged("")
            Box {}
        }
    }

}