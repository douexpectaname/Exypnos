@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.duen.exypnos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.duen.exypnos.ui.app.MainApp
import com.duen.exypnos.ui.theme.ExypnosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSize = calculateWindowSizeClass(this)
            ExypnosTheme {
                MainApp(windowSize)
            }
        }
    }
}
