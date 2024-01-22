package com.example.doan_3tuan.View.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.LoadRss.UiResult
import com.example.doan_3tuan.R
import com.example.doan_3tuan.View.Component.Baiviet_Card
import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.doan_3tuan.Model.BottomNavigationItem
import com.example.doan_3tuan.View.Component.NavBottomAppBar
import com.example.doan_3tuan.ViewModel.BVviewModel.NewsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimKiemScreen(navController: NavController) {
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

    val viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = viewModel.uiState.collectAsStateWithLifecycle()
    val state = homeState.value
    when (state) {
        is UiResult.Fail -> {
            val ctx = LocalContext.current
            val viewModel = NewsViewModel(ctx)
            var news = viewModel.getNews()
            var searchTerm by remember { mutableStateOf(TextFieldValue()) }
            val filteredRssItems = remember(searchTerm.text) {
                news.filter {
                    it.title.contains(
                        searchTerm.text,
                        ignoreCase = true
                    )
                }
            }
            Scaffold(topBar = {
                TopAppBar(
                    modifier = Modifier.height(65.dp),
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B)),
                    title = {
                        TextField(
                            value = searchTerm,
                            onValueChange = { searchTerm = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CircleShape)
                                .size(50.dp),
                            singleLine = false,
                            textStyle = TextStyle(fontSize = 18.sp)
                        )
                    },

                    actions = {
                        TextButton(onClick = { navController.popBackStack() }) {
                            Text(text = "Đóng", fontWeight = FontWeight.ExtraBold, style = TextStyle(color = Color.White))
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
                            }
                        )
                    }
                }
            }) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(filteredRssItems)
                        {
                            Baiviet_Card(item = it) {
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
            }
        }

        is UiResult.Success -> {
            var data = state.data
            var searchTerm by remember { mutableStateOf(TextFieldValue()) }

            val filteredRssItems = remember(searchTerm.text) {
                data.baiviet.filter {
                    it.title.contains(
                        searchTerm.text,
                        ignoreCase = true
                    )
                }
            }
            Scaffold(topBar = {
                TopAppBar(
                    modifier = Modifier.height(65.dp),
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B)),
                    title = {
                        TextField(
                            value = searchTerm,
                            onValueChange = { searchTerm = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CircleShape)
                                .size(50.dp),
                            singleLine = false,
                            textStyle = TextStyle(fontSize = 18.sp)
                        )
                    },

                    actions = {
                        TextButton(onClick = { navController.popBackStack() }) {
                            Text(text = "Đóng", fontWeight = FontWeight.ExtraBold, style = TextStyle(color = Color.White))
                        }
                    }
                )
            }, bottomBar = {
                NavBottomAppBar(navController = navController)
            }) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(filteredRssItems)
                        {
                            Baiviet_Card(item = it) {
                                navController.navigate(NavRoot.chitiet.root + "?link=${it.link}")
                            }
                        }
                    }
                }
            }
        }
    }
}