package com.maxidev.newsyork.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maxidev.newsyork.core.ui.theme.josefinSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentTopBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = title),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = josefinSans
            )
        },
        scrollBehavior = scrollBehavior
    )
}