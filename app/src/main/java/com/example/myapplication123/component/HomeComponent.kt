package com.example.myapplication123.component

import com.arkivanov.decompose.ComponentContext

interface HomeComponent {
    fun navigateToSecondScreen(param: String)
    fun navigateToAboutScreen()
}

class HomeComponentImpl(
    private val onNavigateToSecondScreen: (String) -> Unit,
    private val onNavigateToAboutScreen: () -> Unit,
    componentContext: ComponentContext
) : HomeComponent, ComponentContext by componentContext {

    override fun navigateToSecondScreen(param: String) {
        onNavigateToSecondScreen(param)
    }

    override fun navigateToAboutScreen() {
        onNavigateToAboutScreen()
    }
}