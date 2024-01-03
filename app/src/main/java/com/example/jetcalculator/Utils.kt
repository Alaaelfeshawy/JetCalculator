package com.example.jetcalculator

fun calculateTipAmount(totalBillAmount : Double , tipPercentage : Int) : Double{
    return if (totalBillAmount > 1 && totalBillAmount.toString().isNotEmpty())
        (totalBillAmount * tipPercentage) / 100 else 0.0
}

fun calculateTotalBill( billAmount : Int , tipAmount: Double , splitNumber : Int) : Double{
    return billAmount.plus(tipAmount).div(splitNumber)
}