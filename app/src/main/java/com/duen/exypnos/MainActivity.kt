@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.duen.exypnos

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.window.layout.WindowMetricsCalculator
import com.duen.exypnos.ui.app.AppViewModel
import com.duen.exypnos.ui.app.MainApp
import com.duen.exypnos.ui.theme.ExypnosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ExypnosTheme {
                MainApp()
            }
        }
    }

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory = viewModelFactory {
        addInitializer(AppViewModel::class) {
            AppViewModel(
                WindowSizeClass.calculateFromSize(windowSize)
            )
        }
    }

    private val windowSize: DpSize
        get() {
            val metrics =
                WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this@MainActivity)
            val size = metrics.bounds.toComposeRect().size
            val density = resources.displayMetrics.density
            return DpSize((size.width / density).dp, (size.height / density).dp)
        }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val appViewModel by viewModels<AppViewModel>()
        appViewModel.windowSize = WindowSizeClass.calculateFromSize(windowSize)
    }
}
