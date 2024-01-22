package com.example.doan_3tuan.View.Screen

import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.doan_3tuan.Model.BottomNavigationItem
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.UiResult
import com.example.doan_3tuan.R
import com.example.doan_3tuan.View.Component.Baiviet_Card
import com.example.doan_3tuan.ViewModel.AccountViewModel
import com.example.doan_3tuan.ViewModel.DialogRequireLogin
import com.example.doan_3tuan.ViewModel.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrangChuScreen(navCotroller: NavHostController) {
    val viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = viewModel.uiState.collectAsStateWithLifecycle()
    val state = homeState.value
    val items = listOf(
        BottomNavigationItem(
            "Tin Tức",
            R.drawable.baseline_article_24,
            R.drawable.outline_article_24,
        ),
        BottomNavigationItem(
            "Video",
            R.drawable.baseline_video_library_24,
            R.drawable.outline_video_library_24,
        ),
        BottomNavigationItem(
            "Xu hướng",
            R.drawable.baseline_data_thresholding_24,
            R.drawable.outline_data_thresholding_24,
        ),
        BottomNavigationItem(
            "Tiện ích",
            R.drawable.baseline_dataset_24,
            R.drawable.outline_dataset_24,
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val navdrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val congcu = listOf(
        kotlin.Pair("Tin Tức", R.drawable.baseline_article_24),
        kotlin.Pair("Video", R.drawable.baseline_video_library_24),
        kotlin.Pair("Xu Hướng", R.drawable.baseline_data_thresholding_24),
        kotlin.Pair("Tiện Ích", R.drawable.baseline_dataset_24),
        kotlin.Pair("Thông Báo", R.drawable.outline_circle_notifications_24),
        kotlin.Pair("Đã Lưu", R.drawable.baseline_bookmark_24),
    )

    val accountViewModel: AccountViewModel = viewModel(modelClass = AccountViewModel::class.java)
    var isLoggedIn by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true){
        accountViewModel.checkLoginStatus()
        isLoggedIn =accountViewModel.isLoggedIn
    }
    var idDialog by remember{ mutableStateOf(0)}
    var openDialog by remember { mutableStateOf(false) }

    ModalNavigationDrawer(drawerState = navdrawerState, drawerContent = {
        ModalDrawerSheet {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Công cụ", fontWeight = FontWeight.ExtraBold)
                Divider(Modifier.padding(20.dp))
                congcu.forEach { (tool, icon) ->
                    NavigationDrawerItem(label = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp, horizontal = 20.dp),
                        )
                        {
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = ""
                            )
                            Text(text = tool)
                        }
                    }, selected = false, onClick = { /*TODO*/ })
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
                    IconButton(
                        onClick = {
                            if(!isLoggedIn){
                                idDialog = 1
                                openDialog = true
                            }
                            else {
                                idDialog =2
                                openDialog = true
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_favorite_24),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "")
                    }
                }
            )
        }, bottomBar = {
            NavigationBar(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
                containerColor = Color(0xFF07899B)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index },
                        icon = {
                            Icon(
                                painter = painterResource(id = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon),
                                contentDescription = item.title
                            )
                        })

                }
            }
        }) {

            when (state) {
                is UiResult.Fail -> {
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
                    {
                        Icon(painter = painterResource(id = R.drawable.outline_article_24), contentDescription = "Không có thông tin gì")
                        Text(text = "Không có thông tin gì", fontWeight = FontWeight.Bold)
                    }
                }
                UiResult.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
                is UiResult.Success -> {
                    val data = state.data
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(it),
                    ) {
                        item{
                           LazyRow(Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceAround) {
                               item {
                                   Button(onClick = { /*TODO*/ }) {
                                       Text(text = "Báo người lao động")
                                   }
                                   Button(onClick = { /*TODO*/ }) {
                                       Text(text = "Báo thanh niên")
                                   }
                               }
                            }
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(18.dp)
                            ) {
                                Text(
                                    data.title,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    data.description,
                                )
                            }
                        }
                        items(data.baiviet.take(8)) { item ->
                            Baiviet_Card(item) {
                                navCotroller.navigate(NavRoot.chitiet.root + "?link=${it.link}")
                            }
                        }
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
        else if(idDialog == 2){
            text ="Bạn đã đăng nhập"
        }
        DialogRequireLogin(
            onDiss = {
                openDialog = false
            },
            onConfirm = {
                openDialog = false
                navCotroller.navigate(Screens.Login.route)
            },
            title = text,
        )
    }
}
