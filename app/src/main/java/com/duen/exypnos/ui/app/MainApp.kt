@file:OptIn(
    ExperimentalMaterial3WindowSizeClassApi::class,
)

package com.duen.exypnos.ui.app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.duen.exypnos.ui.theme.ExypnosTheme

@Composable
fun MainApp(windowSize: WindowSizeClass) {
    val navController = rememberNavController()
    Text(text = "hey")

    Row {
        if (windowSize.widthSizeClass >= WindowWidthSizeClass.Expanded) {
            MainRailBar(navController)
        }

        Scaffold(
            bottomBar = {
                if (windowSize.widthSizeClass < WindowWidthSizeClass.Expanded) {
                    MainBottomBar(navController)
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavigationDestination.HOME.name.lowercase(),
            ) {
                NavigationDestination.entries.forEach { dest ->
                    composable(
                        route = dest.name.lowercase(),
                        content = { dest.destination(windowSize, innerPadding) }
                    )
                }
            }
        }
    }
}

@Composable
private fun MainBottomBar(navController: NavController) {
    val current by navController.currentBackStackEntryAsState()

    NavigationBar {
        NavigationDestination.entries.forEach {
            NavigationBarItem(
                selected = it.selected(current),
                onClick = { it.onClick(navController) },
                icon = it.icon,
                label = { Text(text = stringResource(it.nameId)) }
            )
        }
    }
}

@Composable
private fun MainRailBar(navController: NavController) {
    val current by navController.currentBackStackEntryAsState()

    NavigationRail {
        NavigationDestination.entries.forEach {
            NavigationRailItem(
                selected = it.selected(current),
                onClick = { it.onClick(navController) },
                icon = it.icon,
                label = { Text(stringResource(it.nameId)) }
            )
        }
    }
}

@Composable
private fun NavigationDestination.selected(current: NavBackStackEntry?) =
    current?.destination?.hierarchy?.any { it.route == name.lowercase() } == true

private fun NavigationDestination.onClick(navController: NavController) {
    navController.navigate(name.lowercase()) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=1080px,height=2340px,dpi=440"
)
@Composable
fun AppPreview() {
    ExypnosTheme {
        MainApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(420.dp, 1000.dp))
        )
    }
}

@Preview(
    showBackground = true, device = "spec:parent=pixel_5,orientation=landscape",
    showSystemUi = true
)
@Composable
fun AppPreviewLandscape() {
    ExypnosTheme {
        MainApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(10000.dp, 420.dp))
        )
    }
}
