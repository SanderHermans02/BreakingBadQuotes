package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.breakingbadquotes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BBQTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.heisenberg),
                contentDescription = "logo",
                modifier = Modifier.size(48.dp),
            )
        },
        title = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.weight(0.6f))
                Text(stringResource(id = R.string.app_name))
                Spacer(Modifier.weight(1f))
            }
        },
    )
}
