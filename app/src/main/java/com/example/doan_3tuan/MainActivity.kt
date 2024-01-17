package com.example.doan_3tuan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.doan_3tuan.QuachVanSang.ProfileScreen
import com.example.doan_3tuan.QuachVanSang.SettingFontScreen
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember {mutableStateOf(false)}
            var isFontLarge by remember { mutableStateOf(false) }
            var selectedFont by remember { mutableStateOf(false) }
            Doan_3tuanTheme (darkTheme = darkTheme){
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    ProfileScreen(darkTheme = darkTheme, onThemeUpdated = {darkTheme = !darkTheme})
                    SettingFontScreen(
                        isFontLarge = isFontLarge ,
                        selectedFont = selectedFont,
                        onFontUpdated = {selectedFont = !selectedFont },
                        onLargeUpdated = {isFontLarge = !isFontLarge}
                    )
                }
            }
        }
    }
}