package com.example.doan_3tuan.View.Screen

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.doan_3tuan.Model.LoadRss.UiResult
import com.example.doan_3tuan.R

// import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
// import com.example.doan_3tuan.ViewModel.SNViewModel.SaveNews
import com.example.doan_3tuan.View.Component.Baiviet_Card
import com.example.doan_3tuan.ViewModel.AccountViewModel
import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
import com.example.doan_3tuan.ViewModel.DialogErrorLogin
import com.example.doan_3tuan.ViewModel.DialogRequireLogin
import com.example.doan_3tuan.ViewModel.Screens
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chitiet_Screen(navController: NavHostController, linkurl: String) {
    val viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val saveVM : SaveNews = viewModel(modelClass = SaveNews::class.java)
    val homeState = viewModel.uiState.collectAsStateWithLifecycle()
    val state = homeState.value

    val accountViewModel:AccountViewModel = viewModel(modelClass = AccountViewModel::class.java)
    var isLoggedIn by remember { mutableStateOf(false) }

    var idDialog by remember{ mutableStateOf(0)}
    var openDialog by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true){
        accountViewModel.checkLoginStatus()
        isLoggedIn =accountViewModel.isLoggedIn
    }
    when (state) {
        is UiResult.Fail -> {
        }

        UiResult.Loading -> {
        }

        is UiResult.Success -> {
            var color = 0xFF07899B
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "") },
                        colors = TopAppBarDefaults.topAppBarColors(Color(color))
                    )
                },
                bottomBar = {
                    BottomAppBar(actions = {
                        IconButton(onClick = { saveVM.setValueSaveNews(linkurl,"Id") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bookmark_24),
                                contentDescription = ""
                            )
                        }
                    }, containerColor = Color(0xFF07899B))
//                         //Icon Tim
//                         IconButton(
//                             onClick = {
//                                 if(!isLoggedIn){
//                                     idDialog = 1
//                                     openDialog = true
//                                 }
//                                 else {
//                                     // Tăng lượt thích
//                                 }
//                             }
//                         ) {
//                             Icon(
//                                 painter = painterResource(id = R.drawable.baseline_favorite_24),
//                                 contentDescription = ""
//                             )
//                         }
//                     })
                }
            ) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        var back by remember {
                            mutableStateOf(false)
                        }
                        var webView: WebView? = null
                        AndroidView(modifier = Modifier.fillMaxWidth(),factory = {
                            WebView(it).apply {
                                webViewClient =object : WebViewClient(){
                                    override fun onPageStarted(
                                        view: WebView,
                                        url: String?,
                                        favicon: Bitmap?
                                    ){}
                                }
                                settings.javaScriptEnabled = true
                                loadUrl(linkurl)
                                webView = this
                            }
                        }, update = {webView = it})
                    }
                }
            }
        }
    }
    if (openDialog) {
        var text = ""
        if (idDialog == 1) {
            text = "Hãy đăng nhập để xử dụng chức năng"
        }
        DialogRequireLogin(
            onDiss = {
                openDialog = false
            },
            onConfirm = {
                openDialog = false
                navController.navigate(Screens.Login.route)
            },
            title = text,
        )
    }
}

