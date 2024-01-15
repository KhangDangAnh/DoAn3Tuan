package com.example.doan_3tuan.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.doan_3tuan.View.Login.ForgotPasswordScreen
import com.example.doan_3tuan.View.Login.LoginScreen
import com.example.doan_3tuan.View.Login.RegisterScreen
import kotlinx.coroutines.launch

sealed class Screens(val route :String) {
    object Login : Screens("Login_Screen")
    object Register : Screens("Register_Screen")
    object ForgotPassword : Screens("ForgotPassword_Screen")
    object ProfileScreen: Screens("Profile_Screen")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    googleAuthUiClient:GoogleAuthUiClient,
    context: Context
) {

    NavHost(navController = navController, startDestination = Screens.Login.route){
        composable(Screens.Login.route){
            val viewModel =viewModel<SignInGoogleViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = {result ->
                    if(result.resultCode == ComponentActivity.RESULT_OK){
                        lifecycleOwner.lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data?:return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )
            LaunchedEffect(key1 = state.isSignInSuccessful){
                if(state.isSignInSuccessful){
                    Toast.makeText(
                        context,
                        "Sign In successful",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(Screens.ForgotPassword.route)
                }
            }
            LoginScreen(
                navController,
                stateGoogle = state,
                onSignInClick = {
                    lifecycleOwner.lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender?:return@launch
                            ).build()
                        )
                    }
                }
            )
        }
        composable(Screens.Register.route){
            RegisterScreen(navController)
        }
        composable(Screens.ForgotPassword.route){
            ForgotPasswordScreen(navController)
        }
        composable(Screens.ProfileScreen.route){

        }
    }
}