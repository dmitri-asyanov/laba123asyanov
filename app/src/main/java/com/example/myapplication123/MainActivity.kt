package com.example.myapplication123

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication123.ui.theme.MyApplication123Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication123Theme {
                App()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Верхняя панель") },
                actions = {

                    // Snackbar кнопка
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Снекбар",
                                actionLabel = "ОК"
                            )
                        }
                    }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }

                    // Меню
                    Box {
                        var openMenu by remember { mutableStateOf(false) }

                        IconButton(onClick = { openMenu = true }) {
                            Icon(Icons.Default.MoreVert, null)
                        }

                        DropdownMenu(
                            expanded = openMenu,
                            onDismissRequest = { openMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Запуск") },
                                leadingIcon = { Icon(Icons.Default.Rocket, null) },
                                onClick = { openMenu = false }
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

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        // Extended FAB с реакцией на скролл
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Добавить") },
                icon = { Icon(Icons.Default.Add, null) },
                onClick = {
                    showDialog = true
                },
                expanded = !listState.lastScrolledForward
            )
        }

    ) { contentPadding ->

        // Адаптивный отступ
        Column(
            Modifier
                .padding(contentPadding)
                .padding(horizontal = 4.dp)
        ) {

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items((1..50).toList()) {
                    Text("Элемент $it", modifier = Modifier.padding(8.dp))
                }
            }
        }

        // Диалог
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        scope.launch {
                            snackbarHostState.showSnackbar("Подтверждено")
                        }
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Отмена")
                    }
                },
                title = { Text("Подтверждение") },
                text = { Text("Вы хотите выполнить действие?") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyApplication123Theme {
        App()
    }
}