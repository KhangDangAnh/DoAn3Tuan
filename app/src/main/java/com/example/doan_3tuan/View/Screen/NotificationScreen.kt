package com.example.doan_3tuan.View.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.doan_3tuan.Model.Notification
import com.example.doan_3tuan.View.Component.NotificationCard
import com.example.doan_3tuan.ViewModel.NotificationViewModelFirebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavHostController) {
//    var notifications = Notification()
//    var sortByOldest by remember {
//        mutableStateOf(false)
//    }
    var viewModel: NotificationViewModelFirebase= viewModel(modelClass = NotificationViewModelFirebase::class.java)
    val state by rememberUpdatedState(newValue = viewModel.state)
//    var list = mutableListOf<Notification>()
//    val deleteScuccess by viewModel.deleteSuccess.collectAsState()
//    var sortByTime by remember {
//        mutableStateOf(false)
//    }
//    LaunchedEffect(deleteScuccess) {
//        if (deleteScuccess) {
//            viewModel.getAllContact()
//            viewModel.resetDeleteSuccess()
//        }
//    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Thông báo",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
//                    actions = {
//                        Checkbox(
//                            checked = sortByTime,
//                            onCheckedChange = {
//                                sortByTime = it
//                                viewModel.getAllContact()
//                            },
//                        )
//                        Text(text = "Cũ nhất")
//                    },
                    navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }},
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B))
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
            .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            if (state.contactList.isNotEmpty()){
                items(state.contactList){
                    NotificationCard(notification = it, onClickDelete = {viewModel.deleteNotification(it)})
                }
            }else{
                item{ Text(text = "NOT LIST")}
            }
        }
    }
}

