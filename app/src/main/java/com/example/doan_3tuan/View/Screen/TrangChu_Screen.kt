package com.example.doan_3tuan.View.Screen

import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.doan_3tuan.Model.Drawer
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.LoadRss.UiResult
import com.example.doan_3tuan.R
import com.example.doan_3tuan.View.Component.Baiviet_Card
import com.example.doan_3tuan.View.Component.NavBottomAppBar
import com.example.doan_3tuan.ViewModel.BVviewModel.NewsViewModel
import com.example.doan_3tuan.ViewModel.AccountViewModel
import com.example.doan_3tuan.ViewModel.DialogRequireLogin
import com.example.doan_3tuan.ViewModel.Screens
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrangChuScreen(navController: NavHostController) {

    val viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = viewModel.uiState.collectAsStateWithLifecycle()
    val state = homeState.value
    val navdrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val congcu = listOf(
        Drawer("Tin Tức", R.drawable.baseline_article_24, NavRoot.trangchu.root),
        Drawer("Video", R.drawable.baseline_video_library_24, NavRoot.trangchu.root),
        Drawer("Xu Hướng", R.drawable.baseline_data_thresholding_24, NavRoot.xuhuong.root),
        Drawer("Tiện Ích", R.drawable.baseline_dataset_24, NavRoot.trangchu.root),
        Drawer("Thông Báo", R.drawable.outline_circle_notifications_24, NavRoot.luunews.root),
        Drawer("Đã Lưu", R.drawable.baseline_bookmark_24, NavRoot.luunews.root + "?id=${1234}"),
    )
    when (state) {
        is UiResult.Fail -> {
            val ctx = LocalContext.current
            val viewModel = NewsViewModel(ctx)
            var news = viewModel.getNews()
            ModalNavigationDrawer(drawerState = navdrawerState, drawerContent = {
                ModalDrawerSheet {
                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Công cụ", fontWeight = FontWeight.ExtraBold)
                        Divider(Modifier.padding(20.dp))
                        congcu.forEach {
                            NavigationDrawerItem(
                                label = {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp, horizontal = 20.dp),
                                    )
                                    {
                                        Icon(
                                            painter = painterResource(it.Icon),
                                            contentDescription = ""
                                        )
                                        Text(text = it.title)
                                    }
                                },
                                selected = false,
                                onClick = { navController.navigate(it.nav) })

//     val accountViewModel: AccountViewModel = viewModel(modelClass = AccountViewModel::class.java)
//     var isLoggedIn by remember { mutableStateOf(false) }
//     LaunchedEffect(key1 = true){
//         accountViewModel.checkLoginStatus()
//         isLoggedIn =accountViewModel.isLoggedIn
//     }
//     var idDialog by remember{ mutableStateOf(0)}
//     var openDialog by remember { mutableStateOf(false) }

//     ModalNavigationDrawer(drawerState = navdrawerState, drawerContent = {
//         ModalDrawerSheet {
//             Column(
//                 Modifier.fillMaxWidth(),
//                 verticalArrangement = Arrangement.SpaceAround,
//                 horizontalAlignment = Alignment.CenterHorizontally
//             ) {
//                 Text(text = "Công cụ", fontWeight = FontWeight.ExtraBold)
//                 Divider(Modifier.padding(20.dp))
//                 congcu.forEach { (tool, icon) ->
//                     NavigationDrawerItem(label = {
//                         Row(
//                             modifier = Modifier
//                                 .fillMaxWidth()
//                                 .padding(vertical = 10.dp, horizontal = 20.dp),
//                         )
//                         {
//                             Icon(
//                                 painter = painterResource(icon),
//                                 contentDescription = ""
//                             )
//                             Text(text = tool)
                        }
                    }
                }
            }) {
                Scaffold(topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B)),
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    navdrawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                            }
                        },
                        actions = {
                            IconButton(onClick = { navController.navigate(NavRoot.timkiem.root) }) {
                                Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                }, bottomBar = {
                    NavBottomAppBar(navController = navController)
                }) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(it),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        item {
                            Text(
                                text = "Bạn đang ngoại tuyến , vui lòng kiểm tra internet để nhận tin mới .",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        items(news)
                        {
                            Baiviet_Card(item = it)
                            {
                                navController.navigate(NavRoot.chitiet.root + "?link=${it.link}")
                            }
                        }
                    }
                }
            }
        }
        UiResult.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Đang tải", fontWeight = FontWeight.ExtraBold)
            }
        }

        is UiResult.Success -> {
            val data = state.data
            ModalNavigationDrawer(drawerState = navdrawerState, drawerContent = {
                ModalDrawerSheet {
                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Công cụ", fontWeight = FontWeight.ExtraBold)
                        Divider(Modifier.padding(20.dp))
                        congcu.forEach {
                            NavigationDrawerItem(
                                label = {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp, horizontal = 20.dp),
                                    )
                                    {
                                        Icon(
                                            painter = painterResource(it.Icon),
                                            contentDescription = ""
                                        )
                                        Text(text = it.title)
                                    }
                                },
                                selected = false,
                                onClick = { navController.navigate(it.nav) })
                        }
                    }
                }
            }) {
                Scaffold(topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B)),
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    navdrawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                            }
                        },
                        actions = {
                            IconButton(onClick = { navController.navigate(NavRoot.timkiem.root) }) {
                                Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                }, bottomBar = {
                    NavBottomAppBar(navController = navController)
                }) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(it),
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(18.dp)
                            ) {
                                Text( text= "Theo ${data.title}",
                                    fontWeight = FontWeight.ExtraBold,
                                )
                                Text(
                                    data.description,
                                )
                            }
                        }
                        items(data.baiviet.take(8)) { item ->
                            Baiviet_Card(item) {
                                navController.navigate(NavRoot.chitiet.root + "?link=${it.link}")
                            }
                        }
                    }
                }
            }
        }
    }
//    if (openDialog) {
//        var text = ""
//        if (idDialog == 1) {
//            text = "Hãy đăng nhập để xử dụng chức năng"
//        }
//        else if(idDialog == 2){
//            text ="Bạn đã đăng nhập"
//        }
//        DialogRequireLogin(
//            onDiss = {
//                openDialog = false
//            },
//            onConfirm = {
//                openDialog = false
//                navCotroller.navigate(Screens.Login.route)
//            },
//            title = text,
//        )
//    }
}
