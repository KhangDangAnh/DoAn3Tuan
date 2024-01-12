package com.example.doan_3tuan

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doan_3tuan.View.Login.ForgotPasswordScreen
import com.example.doan_3tuan.View.Login.GoogleCard
import com.example.doan_3tuan.View.Login.LoginScreen
import com.example.doan_3tuan.View.Login.RegisterScreen
import com.example.doan_3tuan.View.NavGraph
import com.example.doan_3tuan.View.Screen
import com.example.doan_3tuan.ViewModel.GoogleAuthUiClient
import com.example.doan_3tuan.ViewModel.SignInGoogleViewModel
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

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
