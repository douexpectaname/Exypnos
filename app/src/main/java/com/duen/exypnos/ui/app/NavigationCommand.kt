package com.duen.exypnos.ui.app

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    object Back : NavigationCommand()
    object Halt : NavigationCommand()
    class Direction(val direction: NavDirections) : NavigationCommand()
    class Route(val route: String) : NavigationCommand()
}