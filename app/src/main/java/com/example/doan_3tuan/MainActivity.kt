package com.example.doan_3tuan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.doan_3tuan.ViewModel.NotificationViewModel
import com.example.doan_3tuan.View.VideoScreen
import com.example.doan_3tuan.ViewModel.TrendingViewModel
import com.example.doan_3tuan.ViewModel.VideoViewModel
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelNotification = NotificationViewModel(this)
        val viewModelTrending = TrendingViewModel(this)
        val viewModelVideo  = VideoViewModel(this)
        setContent {
            Doan_3tuanTheme {
                //NotificationScreen(viewModel = viewModel)
                //TrendingScreen(viewModel=viewModelTrending)
                //CareTrendingScreen(viewModel = viewModelTrending)
                VideoScreen(viewModel = viewModelVideo)
            }
        }
    }
}
