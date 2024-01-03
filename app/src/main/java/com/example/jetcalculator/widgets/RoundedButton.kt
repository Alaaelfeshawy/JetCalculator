package com.example.jetcalculator.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val iconButtonModifier = Modifier.size(40.dp)

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    tintColor : Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor : Color =  Color.White,
    elevation : Dp = 4.dp,
    cardClick : ()->Unit,
) {

    Card(
        modifier = modifier
            .size(35.dp)
            .padding(all = 4.dp)
            .background(color = backgroundColor)
            .clickable{cardClick.invoke()}
            .then(iconButtonModifier),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation,
        ),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = CircleShape,
    ) {
        Box(modifier = modifier.align(Alignment.CenterHorizontally)){
            Icon(imageVector = imageVector,
                contentDescription = "icon" ,
                tint = tintColor,
            )
        }
    }
}