package com.example.myapplication123

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.example.myapplication123.component.RootComponent
import com.example.myapplication123.component.RootComponent.Child
import com.example.myapplication123.ui.screens.AboutScreen
import com.example.myapplication123.ui.screens.HomeScreen
import com.example.myapplication123.ui.screens.SecondScreen

@Composable
fun App(rootComponent: RootComponent) {
    Children(rootComponent.childStack) { child ->
        when (val instance = child.instance) {
            is Child.Home -> HomeScreen(instance.component)
            is Child.Second -> SecondScreen(instance.component)
            is Child.About -> AboutScreen(instance.component)
        }
    }
}