package com.example.jetcalculator.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState : MutableState<String>,
    labeledId : String,
    isSingleLine : Boolean,
    isEnabled : Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction : KeyboardActions = KeyboardActions.Default,

) {
    OutlinedTextField(
        modifier= modifier.padding(all = 10.dp),
        value = valueState.value,
        label = {Text(text = labeledId)},
        onValueChange = { newValue : String -> valueState.value = newValue } ,
        textStyle  = TextStyle(fontSize = 18.sp , color = MaterialTheme.colorScheme.onBackground),
        enabled = isEnabled ,
        singleLine = isSingleLine,
        leadingIcon ={ Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "Money ICON" )},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType , imeAction = imeAction),
        keyboardActions = onAction,
    )
}
