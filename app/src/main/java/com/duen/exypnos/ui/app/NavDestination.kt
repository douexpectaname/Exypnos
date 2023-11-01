package com.duen.exypnos.ui.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.duen.exypnos.R
import com.duen.exypnos.ui.account.AccountApp
import com.duen.exypnos.ui.discover.DiscoverApp
import com.duen.exypnos.ui.home.HomeApp
import com.duen.exypnos.ui.home.PrescriptionMakerApp
import com.duen.exypnos.ui.learn.LearnApp

enum class NavDestination(
    val nameId: Int,
    val icon: @Composable () -> Unit,
    val builder: NavGraphBuilder.() -> Unit
) {
    HOME(
        R.string.label_home,
        { Icon(Icons.Outlined.Home, contentDescription = "") },
        {
            navigation("root", "home") {
                composable("root") {
                    HomeApp()
                }
                navigation("generate", "prescription") {
                    composable("generate") {
                        PrescriptionMakerApp()
                    }
                }
            }
        }
    ),
    LEARN(
        R.string.label_learn,
        {
            Icon(
                Icons.Outlined.School,
                contentDescription = ""
            )
        },
        {
            composable("learn") {
                LearnApp()
            }
        }
    ),
    DISCOVER(
        R.string.label_discover,
        {
            Icon(
                painterResource(id = R.drawable.ic_chart_timeline_variant_shimmer),
                contentDescription = ""
            )
        },
        {
            composable("discover") {
                DiscoverApp()
            }
        }
    ),
    ACCOUNT(
        R.string.label_account,
        {
            Icon(Icons.Outlined.Person, contentDescription = "")
        },
        {
            composable("account") {
                AccountApp()
            }
        }
    )
}
