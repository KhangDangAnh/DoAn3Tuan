package com.example.doan_3tuan

import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.doan_3tuan.ViewModel.RootGraph
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Doan_3tuanTheme {
                val navRootController = rememberNavController()
                RootGraph(navHostController = navRootController)
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //RootGraph(navHostController = navRootController)
                    RootGraph(navHostController = navRootController)
                }
            }
        }
    }
}