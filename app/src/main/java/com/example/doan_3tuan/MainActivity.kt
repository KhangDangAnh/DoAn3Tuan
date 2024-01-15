package com.example.doan_3tuan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.doan_3tuan.ViewModel.NavGraph
import com.example.doan_3tuan.ViewModel.GoogleAuthUiClient
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {
    internal val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Doan_3tuanTheme {
                // A surface container using the 'background' color from the theme
                var navcontroller = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavGraph(
                        navController = navcontroller,
                        lifecycleOwner = this,
                        googleAuthUiClient = googleAuthUiClient,
                        context = applicationContext
                    )
                }
            }
        }
    }
}
