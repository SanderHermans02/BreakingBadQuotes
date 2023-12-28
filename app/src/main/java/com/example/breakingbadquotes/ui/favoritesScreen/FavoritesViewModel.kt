package com.example.breakingbadquotes.ui.favoritesScreen

import android.util.Log
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {
    init {
        Log.i("vm inspection", "FavoritesViewModel init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("vm inspection", "FavoritesViewModel cleared")
    }
}
