package com.example.doan_3tuan.QuachVanSang

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
class SettingViewModel (private val context: Context): ViewModel() {
    var theme by mutableStateOf(false)
    var font by mutableStateOf(false)
    var currentFontName by mutableStateOf("Inria Sans")
    var large by mutableStateOf(false)

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("setting", Context.MODE_PRIVATE)
    init {
        theme = getSaveThemeState()
        font = getSaveFontState()
        large = getSaveLargeState()
    }
    fun saveThemeState(theme: Boolean){
        sharedPreferences.edit().putBoolean("theme_key",theme).apply()
        this.theme = theme
    }
    fun getSaveThemeState(): Boolean {
        return sharedPreferences.getBoolean("theme_key", false)
    }

    fun saveFontState(font: Boolean, fontName: String){
        sharedPreferences.edit().putBoolean("font_key",font).apply()
        this.font = font
        this.currentFontName = fontName
    }
    fun getSaveFontState(): Boolean {
        return sharedPreferences.getBoolean("font_key", false)
    }

    fun saveLargeState(theme: Boolean){
        sharedPreferences.edit().putBoolean("large_key",large).apply()
        this.large = large
    }
    fun getSaveLargeState(): Boolean {
        return sharedPreferences.getBoolean("large_key", false)
    }
}