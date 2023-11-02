
package com.duen.exypnos.ui.learn

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.duen.exypnos.R
import com.duen.exypnos.ui.common.ScaffoldPinedTopBar

@Composable
fun LearnApp() {
    ScaffoldPinedTopBar(
        title = { Text(stringResource(id = R.string.title_learn)) }
    ) {
        for (i in 0..100) {
            Text(text = "I am educative")
        }
    }
}