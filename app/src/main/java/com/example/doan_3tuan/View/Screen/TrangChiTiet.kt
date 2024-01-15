package com.example.doan_3tuan.View.Screen

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.UiResult
import com.example.doan_3tuan.R
import com.example.doan_3tuan.View.Component.Baiviet_Card
import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chitiet_Screen(navController: NavHostController, linkurl: String) {
    var backEnable by remember {
        mutableStateOf(false)
    }
    var webView : WebView? = null
    val viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = viewModel.uiState.collectAsStateWithLifecycle()
    val state = homeState.value
    when (state) {
        is UiResult.Fail -> {
        }

        UiResult.Loading -> {
        }

        is UiResult.Success -> {
            val data = state.data
            //val linkurl = "https://thanhnien.vn/sap-khai-thong-doan-duong-thuong-xuyen-ket-xe-sat-nui-ba-hoa-o-tpquy-nhon-185240112150211598.htm"
            val news = listOf(
                kotlin.Pair(R.drawable.img, "News"),
                kotlin.Pair(R.drawable.img, "News"),
                kotlin.Pair(R.drawable.img, "News"),
                kotlin.Pair(R.drawable.img, "News"),
                kotlin.Pair(R.drawable.img, "News"),
                kotlin.Pair(R.drawable.img, "News"),
                kotlin.Pair(R.drawable.img, "News"),
                kotlin.Pair(R.drawable.img, "News")
            )
            var color = 0xFF07899B
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "") },
                        navigationIcon = {
                            IconButton(onClick = {  webView?.goBack();navController.popBackStack()}) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(Color(color))
                    )
                },
                bottomBar = {
                    BottomAppBar(actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bookmark_24),
                                contentDescription = ""
                            )
                        }
                    })
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
                        AndroidView(modifier = Modifier.fillMaxWidth(),factory = {
                            WebView(it).apply {
                                webViewClient =object : WebViewClient(){
                                    override fun onPageStarted(
                                        view: WebView,
                                        url: String?,
                                        favicon: Bitmap?
                                    ) {
                                        backEnable = view.canGoBack()
                                    }
                                }
                                loadUrl(linkurl)
                                webView = this
                            }
                        }, update = {webView = it})
                    }
                }
            }
        }
    }
}