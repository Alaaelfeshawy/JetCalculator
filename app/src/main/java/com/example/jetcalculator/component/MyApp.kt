package com.example.jetcalculator.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.jetcalculator.ui.theme.JetCalculatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(content : @Composable ()->Unit) {
    JetCalculatorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = { TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        titleContentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("")
                    }
                ) }
            ) {
                Column(modifier = Modifier.padding(it)) {
                    content()
                }
            }
        }
    }
}

