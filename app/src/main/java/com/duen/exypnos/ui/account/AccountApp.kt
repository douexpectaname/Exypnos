package com.duen.exypnos.ui.account

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.duen.exypnos.R
import com.duen.exypnos.ui.common.ScaffoldPinedTopBar

@Composable
fun AccountApp() {
    ScaffoldPinedTopBar(
        title = { Text(stringResource(id = R.string.title_account)) }
    ) {
        Text(text = "whoami")
    }
}