package com.example.doan_3tuan

import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.doan_3tuan.ViewModel.NotificationViewModel
import com.example.doan_3tuan.View.VideoScreen
import com.example.doan_3tuan.ViewModel.TrendingViewModel
import com.example.doan_3tuan.ViewModel.VideoViewModel
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.doan_3tuan.View.SangQuach.ProfileScreen
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.doan_3tuan.View.Screen.Chitiet_Screen
import com.example.doan_3tuan.ViewModel.RootGraph
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelNotification = NotificationViewModel(this)
        val viewModelTrending = TrendingViewModel(this)
        val viewModelVideo  = VideoViewModel(this)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            Doan_3tuanTheme (darkTheme = darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ProfileScreen(darkTheme = darkTheme, onThemeUpdated = { darkTheme = !darkTheme})
      
        }
    }
}
                val navRootController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   RootGraph(navHostController = navRootController)
                }
            }
        }
    }
}
