package com.duen.exypnos.ui.app

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.duen.exypnos.ui.theme.ExypnosTheme

@Composable
fun MainApp() {
    val appViewModel = viewModel<AppViewModel>()
    val navController = rememberNavController()
    val navCommand by appViewModel.navigation.collectAsStateWithLifecycle()

    LaunchedEffect(navCommand) {
        when (navCommand) {
            is NavigationCommand.Back -> navController.navigateUp()
            is NavigationCommand.Direction ->
                navController.navigate(
                    (navCommand as NavigationCommand.Direction).direction,
                )

            is NavigationCommand.Route ->
                navController.navigate((navCommand as NavigationCommand.Route).route) {
                    launchSingleTop = true
                    restoreState = true
                }

            NavigationCommand.Halt -> {}
        }
    }

    Row {
        if (appViewModel.windowSize.widthSizeClass >= WindowWidthSizeClass.Expanded) {
            MainRailBar(navController)
        }

        Scaffold(
            bottomBar = {
                if (appViewModel.windowSize.widthSizeClass < WindowWidthSizeClass.Expanded) {
                    MainBottomBar(navController)
                }
            },
        ) { _ ->
            NavHost(
                navController = navController,
                startDestination = NavDestination.HOME.name.lowercase(),
            ) {
                NavDestination.entries.forEach { dest ->
                    dest.builder(this)
                }
            }
        }
    }
}

@Composable
private fun MainBottomBar(navController: NavController) {
    val current by navController.currentBackStackEntryAsState()

    NavigationBar {
        NavDestination.entries.forEach {
            Log.d("selected", "$it ${current?.destination?.hierarchy?.toList()}")
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
        NavDestination.entries.forEach {
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
private fun NavDestination.selected(current: NavBackStackEntry?) =
    current?.destination?.hierarchy?.any { it.route == name.lowercase() } == true

private fun NavDestination.onClick(navController: NavController) {
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
        MainApp()
    }
}

@Preview(
    showBackground = true, device = "spec:parent=pixel_5,orientation=landscape",
    showSystemUi = true
)
@Composable
fun AppPreviewLandscape() {
    ExypnosTheme {
        MainApp()
    }
}
