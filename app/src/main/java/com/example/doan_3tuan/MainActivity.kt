package com.example.doan_3tuan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doan_3tuan.QuachVanSang.ProfileScreen
import com.example.doan_3tuan.QuachVanSang.SettingFontScreen
import com.example.doan_3tuan.QuachVanSang.SettingViewModel
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = SettingViewModel(this)
            viewModel.theme = viewModel.getSaveThemeState()
            Doan_3tuanTheme (darkTheme = viewModel.theme){
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SettingFontScreen()
                }
            }
        }
    }
}