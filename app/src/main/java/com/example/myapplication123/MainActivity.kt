package com.example.myapplication123

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.example.myapplication123.component.RootComponentImpl
import com.example.myapplication123.ui.theme.MyApplication123Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(defaultComponentContext())

        setContent {
            MyApplication123Theme {
                App(rootComponent)
            }
        }
    }
}