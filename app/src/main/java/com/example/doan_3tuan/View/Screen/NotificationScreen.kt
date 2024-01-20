package com.example.doan_3tuan.View.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
    var state = viewModel.state
    var list = mutableListOf<Notification>()
    val deleteScuccess by viewModel.deleteSuccess.collectAsState()
    LaunchedEffect(deleteScuccess) {
        if (deleteScuccess) {
            // Xóa thành công, có thể thực hiện các hành động cần thiết (ví dụ: hiển thị thông báo)

            // Load lại trang
            viewModel.getAllContact()

            // Đặt lại trạng thái xóa thành công để tránh việc gọi lại liên tục
            viewModel.resetDeleteSuccess()
        }
    }
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
//                            checked = sortByOldest,
//                            onCheckedChange = {
//                                if (sortByOldest) {
//                                    notifications = viewModel.getNotifications()
//                                    sortByOldest = false
//                                } else {
//                                    notifications = viewModel.sortNotificationByOldest()
//                                    sortByOldest = true
//                                }
//                            })
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
