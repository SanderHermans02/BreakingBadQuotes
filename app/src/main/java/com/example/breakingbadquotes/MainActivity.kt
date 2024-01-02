package com.example.breakingbadquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.breakingbadquotes.ui.BBQApp
import com.example.breakingbadquotes.ui.theme.BreakingBadQuotesTheme
import com.example.breakingbadquotes.ui.util.QuoteNavigationType

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingBadQuotesTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            BBQApp(windowSize, QuoteNavigationType.BOTTOM_NAVIGATION)
                        }
                        else -> {
                            BBQApp(windowSize, QuoteNavigationType.NAVIGATION_RAIL)
                        }
                    }
                }
            }
        }
    }
}
