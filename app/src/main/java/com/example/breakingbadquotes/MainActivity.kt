package com.example.breakingbadquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.breakingbadquotes.ui.BBQApp
import com.example.breakingbadquotes.ui.theme.BreakingBadQuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingBadQuotesTheme {
                Surface {
                    BBQApp()
                }
            }
        }
    }
}

