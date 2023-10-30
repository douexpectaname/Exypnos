package com.duen.exypnos.ui.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.duen.exypnos.R
import com.duen.exypnos.ui.home.HomeApp

enum class NavigationDestination(
    val nameId: Int,
    val icon: @Composable () -> Unit,
    val destination: @Composable (WindowSizeClass, PaddingValues) -> Unit
) {
    HOME(
        R.string.label_home,
        { Icon(Icons.Outlined.Home, contentDescription = "") },
        { w, p -> HomeApp(w, p) }
    ),
    LEARN(
        R.string.label_learn,
        {
            Icon(
                Icons.Outlined.School,
                contentDescription = ""
            )
        },
        { w, p -> }
    ),
    DISCOVER(
        R.string.label_discover,
        {
            Icon(
                painterResource(id = R.drawable.ic_chart_timeline_variant_shimmer),
                contentDescription = ""
            )
        },
        { w, p -> }
    ),
    ACCOUNT(
        R.string.label_account,
        {
            Icon(Icons.Outlined.Person, contentDescription = "")
        },
        { w, p -> }
    )
}