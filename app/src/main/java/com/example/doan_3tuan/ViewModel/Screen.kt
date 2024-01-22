package com.example.doan_3tuan.ViewModel

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
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
import androidx.navigation.navArgument
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.QuachVanSang.ProfileScreen
import com.example.doan_3tuan.View.Login.ForgotPasswordScreen
import com.example.doan_3tuan.View.Login.LoginScreen
import com.example.doan_3tuan.View.Login.RegisterScreen
import com.example.doan_3tuan.View.Screen.Chitiet_Screen
import com.example.doan_3tuan.View.Screen.SaveNewsScreen
import com.example.doan_3tuan.View.Screen.TimKiemScreen
import com.example.doan_3tuan.View.Screen.TrangChuScreen
import com.example.doan_3tuan.View.Screen.XuhuongScreen
import com.example.doan_3tuan.View.VideoScreen
import kotlinx.coroutines.launch

sealed class Screens(val route :String) {
    object Login : Screens("Login_Screen")
    object Register : Screens("Register_Screen")
    object ForgotPassword : Screens("ForgotPassword_Screen")
    object HomeScreen:Screens("Home_Screen")

    object chitiet : Screens("chitiet")

    object timkiem : Screens("timkiem")
    object xuhuong : Screens("xuhuong")
    object tienich : Screens("tienich")

    object luunews : Screens("luunews")
    //object TrangChiTiet:Screens("TrangChiTiet_Screen")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    googleAuthUiClient:GoogleAuthUiClient,
    context: Context
) {

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route){
        composable(Screens.Login.route){
            val googleViewModel =viewModel<SignInGoogleViewModel>()
            val stateGoogle by googleViewModel.state.collectAsStateWithLifecycle()

            val accountViewModel = viewModel<AccountViewModel>()
            val stateAccount = accountViewModel.state

            LaunchedEffect(key1 = Unit){
                if(googleAuthUiClient.getSignedInUser() !=null){
                    navController.navigate(Screens.ForgotPassword.route)
                }
            }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = {result ->
                    if(result.resultCode == RESULT_OK){
                        lifecycleOwner.lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data?:return@launch
                            )
                            googleViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )
            LaunchedEffect(key1 = stateGoogle.isSignInSuccessful){
                if(stateGoogle.isSignInSuccessful){
                    Toast.makeText(
                        context,
                        "Đăng nhập thành công",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate(Screens.HomeScreen.route)
                    googleViewModel.resetData()
                }
            }

            LaunchedEffect(key1 = stateAccount){
                if(stateAccount.success){
                    Toast.makeText(
                        context,
                        "Đăng nhập thành công",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(Screens.HomeScreen.route)
                }

            }
            LoginScreen(
                navController,
                stateGoogle = stateGoogle,
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
            val accountViewModel = viewModel<AccountViewModel>()
            val stateAccount = accountViewModel.state

            LaunchedEffect(key1 = stateAccount){
                if(stateAccount.success){
                    Toast.makeText(
                        context,
                        "Đăng ký thành công",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(Screens.Login.route)
                }
            }
            RegisterScreen(navController)
        }
        composable(Screens.ForgotPassword.route){
            ForgotPasswordScreen(
                navController,
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOutClick = {
                    lifecycleOwner.lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Sign out",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.popBackStack()
                    }
                },
            )
        }
        composable(Screens.HomeScreen.route){
            TrangChuScreen(navController)
        }
        composable(
            NavRoot.chitiet.root + "?link={link}",
            arguments = listOf(navArgument("link") { nullable = true })
        )
        {
            val url = it.arguments?.getString("link")
            Chitiet_Screen(navController, url ?: "")
        }
        composable(NavRoot.timkiem.root)
        {
            TimKiemScreen(navController = navController)
        }
        composable(
            NavRoot.luunews.root + "?id={id}",
            arguments = listOf(navArgument("id") { nullable = true })
        )
        {
            val id = it.arguments?.getString("id")
            SaveNewsScreen(navController, id ?: "")
        }
        composable(NavRoot.xuhuong.root)
        {
            XuhuongScreen(navController = navController)
        }
        composable(NavRoot.video.root)
        {
            VideoScreen(navController)
        }
        composable(NavRoot.tienich.root)
        {
            ProfileScreen(navController)
        }

    }
}