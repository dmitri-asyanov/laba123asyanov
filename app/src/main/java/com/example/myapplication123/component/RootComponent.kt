package com.example.myapplication123.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

interface RootComponent {
    val childStack: Value<ChildStack<Config, Child>>

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        class Second(val component: SecondComponent) : Child
        class About(val component: AboutComponent) : Child
    }
}

@Serializable
sealed interface Config {
    @Serializable
    data object Home : Config

    @Serializable
    data class Second(val param: String) : Config

    @Serializable
    data object About : Config
}

class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.Home -> RootComponent.Child.Home(
                HomeComponentImpl(
                    onNavigateToSecondScreen = { param ->
                        navigation.pushNew(Config.Second(param))
                    },
                    onNavigateToAboutScreen = {
                        navigation.bringToFront(Config.About)
                    },
                    componentContext = componentContext
                )
            )

            is Config.Second -> RootComponent.Child.Second(
                SecondComponentImpl(
                    param = config.param,
                    onGoBack = { navigation.pop() },
                    componentContext = componentContext
                )
            )

            is Config.About -> RootComponent.Child.About(
                AboutComponentImpl(
                    onGoBack = { navigation.pop() },
                    componentContext = componentContext
                )
            )
        }
    }
}