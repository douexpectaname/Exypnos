package com.duen.exypnos.ui.discover

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.duen.exypnos.R
import com.duen.exypnos.ui.common.ScaffoldPinedTopBar

@Composable
fun DiscoverApp() {
    ScaffoldPinedTopBar(
        title = { Text(stringResource(id = R.string.title_discover)) }
    ) {
        Text(text = "what's up?")
    }
}