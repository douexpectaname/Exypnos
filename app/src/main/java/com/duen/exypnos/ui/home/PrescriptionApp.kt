@file:OptIn(ExperimentalMaterial3Api::class)

package com.duen.exypnos.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.duen.exypnos.R
import com.duen.exypnos.ui.common.ScaffoldPinedTopBar

@Composable
fun PrescriptionMakerApp() {
    ScaffoldPinedTopBar(
        title = { Text(stringResource(id = R.string.title_prescription)) }
    ) {
        Text(text = "heeeeey")
    }
}


