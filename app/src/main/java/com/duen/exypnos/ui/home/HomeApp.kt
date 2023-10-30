@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)

package com.duen.exypnos.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FindInPage
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.duen.exypnos.R
import com.duen.exypnos.ui.theme.ExypnosTheme
import com.duen.exypnos.ui.theme.PaddingCommon
import com.duen.exypnos.ui.theme.PaddingSmall

@Composable
fun HomeApp(windowSize: WindowSizeClass, innerPadding: PaddingValues) {
    var query by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    Box(
        Modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            query = query,
            onQueryChange = { query = it },
            onSearch = {},
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = stringResource(id = R.string.text_home_search_hint)) },
            leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "") },
            trailingIcon = {
                AnimatedVisibility(active, enter = fadeIn(), exit = fadeOut()) {
                    IconButton(
                        onClick = {
                            if (query.isEmpty()) {
                                active = false
                            } else {
                                query = ""
                            }
                        },
                        content = { Icon(Icons.Outlined.Close, contentDescription = "") }
                    )
                }
            }
        ) {

        }

        Column(
            Modifier
                .padding(innerPadding)
                .padding(top = 72.dp)b
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.label_personal_recommendations),
                style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.padding(start = PaddingCommon + PaddingSmall, bottom = PaddingSmall)
            )
            HomeRecommendations()
            HomePrimaryActions()
        }
    }
}

@Composable
private fun HomeRecommendations() {
    val colorScheme = MaterialTheme.colorScheme
    LazyRow(Modifier.fillMaxWidth()) {
        item {
            Spacer(Modifier.width(PaddingCommon))
        }
        items(
            listOf(
                colorScheme.primary to Icons.Default.AcUnit,
                colorScheme.tertiary to Icons.Default.Notifications,
                colorScheme.secondaryContainer to Icons.Default.AccountBalanceWallet,
                colorScheme.error to Icons.Default.Notifications)
        ) {
            RecommendationItem(
                avatar = {
                    Box(
                        modifier = Modifier.background(it.first),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            it.second,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.contentColorFor(it.first)
                        )
                    }
                },
                label = { Text("recommendation") },
                modifier = Modifier.padding(PaddingSmall)
            )
        }
    }
}

@Composable
private fun RecommendationItem(
    avatar: @Composable () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Surface(shape = CircleShape, modifier = Modifier.size(48.dp)) {
            avatar()
        }
        label()
    }
}

@Composable
private fun HomePrimaryActions() {
    Row(Modifier.padding(horizontal = PaddingCommon)) {
        PrimaryActionCard(
            icon = { Icon(Icons.Outlined.AutoAwesome, contentDescription = "") },
            label = { Text(text = stringResource(id = R.string.action_prescription)) },
            modifier = Modifier
                .weight(0.5f)
                .padding(PaddingSmall)
        )
        PrimaryActionCard(
            icon = { Icon(Icons.Outlined.FindInPage, contentDescription = "") },
            label = { Text(text = stringResource(id = R.string.action_acupuncture)) },
            modifier = Modifier
                .weight(0.5f)
                .padding(PaddingSmall)
        )
    }
    Row(Modifier.padding(horizontal = PaddingCommon)) {
        PrimaryActionCard(
            icon = { Icon(Icons.Outlined.LocalHospital, contentDescription = "") },
            label = { Text(text = stringResource(id = R.string.action_doctor)) },
            modifier = Modifier
                .weight(0.5f)
                .padding(PaddingSmall)
        )
        PrimaryActionCard(
            icon = { Icon(Icons.Outlined.HealthAndSafety, contentDescription = "") },
            label = { Text(text = stringResource(id = R.string.action_constitution)) },
            modifier = Modifier
                .weight(0.5f)
                .padding(PaddingSmall)
        )
    }
}

@Composable
private fun PrimaryActionCard(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Row(Modifier.padding(PaddingCommon)) {
            Box(Modifier.align(Alignment.CenterVertically)) {
                icon()
            }
            Box(
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = PaddingSmall)
            ) {
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleMedium) {
                    label()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    ExypnosTheme {
        HomeApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(420.dp, 1000.dp)),
            innerPadding = PaddingValues()
        )
    }
}


@Preview(
    showBackground = true, device = "spec:parent=pixel_5,orientation=landscape",
    showSystemUi = true
)
@Composable
fun AppPreviewLandscape() {
    ExypnosTheme {
        HomeApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(10000.dp, 420.dp)),
            innerPadding = PaddingValues()
        )
    }
}
