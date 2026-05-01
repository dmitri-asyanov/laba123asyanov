package com.example.myapplication123.component

import com.arkivanov.decompose.ComponentContext

interface AboutComponent {
    fun goBack()
}

class AboutComponentImpl(
    private val onGoBack: () -> Unit,
    componentContext: ComponentContext
) : AboutComponent, ComponentContext by componentContext {

    override fun goBack() {
        onGoBack()
    }
}