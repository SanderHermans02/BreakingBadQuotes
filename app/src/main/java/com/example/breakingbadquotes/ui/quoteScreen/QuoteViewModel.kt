package com.example.breakingbadquotes.ui.quoteScreen

import android.util.Log
import androidx.lifecycle.ViewModel

class QuoteViewModel : ViewModel() {
    init {
        Log.i("vm inspection", "AboutViewModel init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("vm inspection", "AboutViewModel cleared")
    }
}
