@file:OptIn(ExperimentalMaterial3Api::class)

package com.duen.exypnos.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.duen.exypnos.R

@Composable
fun PrescriptionMakerApp(innerPadding: PaddingValues) {
    CenterAlignedTopAppBar(title = { Text(stringResource(id = R.string.title_prescription)) })
    Box(Modifier.padding(innerPadding)) {

    }
}


