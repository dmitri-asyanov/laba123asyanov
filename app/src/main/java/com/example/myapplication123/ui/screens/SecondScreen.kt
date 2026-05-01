package com.example.myapplication123.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication123.component.SecondComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(component: SecondComponent) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Второй экран") },
                navigationIcon = {
                    IconButton(onClick = { component.goBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            SelectionContainer {
                Text("Параметр: ${component.param}")
            }

            Button(
                onClick = { component.goBack() },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Назад")
            }
        }
    }
}