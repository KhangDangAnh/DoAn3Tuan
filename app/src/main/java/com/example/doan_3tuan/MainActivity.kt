package com.example.doan_3tuan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.doan_3tuan.View.Login.ForgotPasswordScreen
import com.example.doan_3tuan.View.Login.LoginScreen
import com.example.doan_3tuan.View.Login.RegisterScreen
import com.example.doan_3tuan.View.NavGraph
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Doan_3tuanTheme {
                // A surface container using the 'background' color from the theme
                var navcontroller = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavGraph(navController = navcontroller)
                }
            }
        }
    }
}
