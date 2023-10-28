@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalLayoutApi::class
)

package com.duen.exypnos.ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duen.exypnos.R
import com.duen.exypnos.ui.home.HomeApp
import com.duen.exypnos.ui.rememberCurrentDestination
import com.duen.exypnos.ui.theme.ExypnosTheme

@Composable
fun MainApp(windowSize: WindowSizeClass) {
    val topbarBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState()
    )
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            MainTopBar(
                scrollBehavior = topbarBehavior
            )
        },
        modifier = Modifier.nestedScroll(topbarBehavior.nestedScrollConnection),
        bottomBar = { MainBottomBar(windowSize, navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeApp()
            }
            composable("learn") {
                Text(text = "learn something")
            }
            composable("discover") {
                Text(text = "discover something")
            }
            composable("account") {
                Text(text = "what is self")
            }
        }
    }
}

@Composable
private fun MainTopBar(scrollBehavior: TopAppBarScrollBehavior) {
    LargeTopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        scrollBehavior = scrollBehavior
    )
}

private enum class NavigationDestination(val nameId: Int, val icon: @Composable () -> Unit) {
    HOME(R.string.label_home, { Icon(Icons.Outlined.Home, contentDescription = "") }),
    LEARN(
        R.string.label_learn,
        {
            Icon(
                painterResource(id = R.drawable.ic_school_outline),
                contentDescription = ""
            )
        }
    ),
    DISCOVER(R.string.label_discover, {
        Icon(
            painterResource(id = R.drawable.ic_chart_timeline_variant_shimmer),
            contentDescription = ""
        )
    }),
    ACCOUNT(R.string.label_account, {
        Icon(Icons.Outlined.Person, contentDescription = "")
    })
}

@Composable
private fun MainBottomBar(windowSize: WindowSizeClass, navController: NavController) {
    if (windowSize.widthSizeClass >= WindowWidthSizeClass.Expanded) {
        return
    }
    val current = rememberCurrentDestination(navController)

    NavigationBar {
        NavigationDestination.entries.forEach {
            NavigationBarItem(
                selected = it.name.startsWith(
                    current?.route ?: "", ignoreCase = true
                ),
                onClick = { navController.navigate(it.name.lowercase()) },
                icon = it.icon,
                label = { Text(text = stringResource(it.nameId)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExypnosTheme {
        MainApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(420.dp, 700.dp))
        )
    }
}
