package com.example.doan_3tuan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.doan_3tuan.View.Screen.NotificationScreen
import com.example.doan_3tuan.View.Screen.TrangChuScreen
import com.example.doan_3tuan.View.Screen.TrendingScreen
import com.example.doan_3tuan.View.Screen.VideoScreen
import com.example.doan_3tuan.ViewModel.TrendingViewModel
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val viewModelTrending = TrendingViewModel(this)
        setContent {
            Doan_3tuanTheme {
                var navController = rememberNavController()
                //MainScreen()
                //VideoScreen()
                NotificationScreen(navController)
                //TrendingScreen(viewModel = viewModelTrending,navController)
            }
        }
    }
}
