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

/**
 * The main Activity class for the Breaking Bad Quotes application.
 */
class MainActivity : ComponentActivity() {
    /**
     * Initializes the activity and sets the content view.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingBadQuotesTheme {
                Surface {
                    // Determine the size class of the window to decide on the navigation type
                    val windowSize = calculateWindowSizeClass(activity = this)
                    when (windowSize.widthSizeClass) {
                        // Use bottom navigation for compact window sizes
                        WindowWidthSizeClass.Compact -> {
                            BBQApp(QuoteNavigationType.BOTTOM_NAVIGATION)
                        }
                        // Use rail navigation for all other window sizes
                        else -> {
                            BBQApp(QuoteNavigationType.NAVIGATION_RAIL)
                        }
                    }
                }
            }
        }
    }
}
