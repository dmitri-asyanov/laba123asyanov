package com.example.myapplication123.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication123.component.HomeComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(component: HomeComponent) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var openMenu by remember { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Домашний экран") },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Снекбар",
                                duration = SnackbarDuration.Long
                            )
                        }
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Снекбар")
                    }

                    Box {
                        IconButton(onClick = { openMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Меню")
                        }

                        DropdownMenu(
                            expanded = openMenu,
                            onDismissRequest = { openMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("О программе") },
                                leadingIcon = { Icon(Icons.Outlined.Settings, null) },
                                onClick = {
                                    openMenu = false
                                    component.navigateToAboutScreen()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Настройки") },
                                leadingIcon = { Icon(Icons.Outlined.Settings, null) },
                                onClick = { openMenu = false }
                            )
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Параметр второго экрана") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { component.navigateToSecondScreen(text) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Перейти на второй экран")
            }
        }
    }
}

