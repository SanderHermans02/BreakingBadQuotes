package com.example.breakingbadquotes.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.example.breakingbadquotes.ui.navigation.QuoteOverviewScreen

@Composable
fun BBQNavigationRail(selectedDestination: NavDestination?, onTabPressed: (String) -> Unit, modifier: Modifier = Modifier) {
    NavigationRail(modifier = modifier, containerColor = MaterialTheme.colorScheme.primaryContainer) {
        for (navItem in QuoteOverviewScreen.values()) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
            )
        }
    }
}
