package com.duen.exypnos.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination

@Composable
fun rememberCurrentDestination(navController: NavController): NavDestination? {
    var current by remember {
        mutableStateOf(navController.currentDestination)
    }
    val navChangeListener = NavController.OnDestinationChangedListener { _, dest, _ ->
        current = dest
    }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener(navChangeListener)
    }

    DisposableEffect(navController) {
        onDispose {
            navController.removeOnDestinationChangedListener(navChangeListener)
        }
    }

    return current
}