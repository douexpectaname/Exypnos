@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
)

package com.duen.exypnos.ui.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duen.exypnos.ui.rememberCurrentDestination
import com.duen.exypnos.ui.theme.ExypnosTheme

@Composable
fun MainApp(windowSize: WindowSizeClass) {
    val navController = rememberNavController()

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
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                NavigationDestination.entries.forEach { dest ->
                    composable(
                        route = dest.name.lowercase(),
                        enterTransition = {
                            fadeIn()
                        },
                        exitTransition = {
                            fadeOut()
                        },
                        content = { dest.destination(windowSize) }
                    )
                }
            }
        }
    }

}

@Composable
private fun MainBottomBar(navController: NavController) {
    val current = rememberCurrentDestination(navController)

    NavigationBar {
        NavigationDestination.entries.forEach {
            NavigationBarItem(
                selected = current?.route?.startsWith(it.name, ignoreCase = true) == true,
                onClick = { navController.navigate(it.name.lowercase()) },
                icon = it.icon,
                label = { Text(text = stringResource(it.nameId)) }
            )
        }
    }
}

@Composable
private fun MainRailBar(navController: NavController) {
    val current = rememberCurrentDestination(navController)

    NavigationRail {
        NavigationDestination.entries.forEach {
            NavigationRailItem(
                selected = current?.route?.startsWith(it.name, ignoreCase = true) == true,
                onClick = { navController.navigate(it.name.lowercase()) },
                icon = it.icon,
                label = { Text(stringResource(it.nameId)) }
            )
        }
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
