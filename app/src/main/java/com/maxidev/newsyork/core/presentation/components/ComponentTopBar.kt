package com.maxidev.newsyork.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.newsyork.R
import com.maxidev.newsyork.core.ui.theme.josefinSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentTopBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = title),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = josefinSans
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.poweredby_nytimes_200c),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
        },
        scrollBehavior = scrollBehavior
    )
}