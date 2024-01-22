package com.example.doan_3tuan.View.Screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.doan_3tuan.Model.Notification
import com.example.doan_3tuan.View.Component.NotificationCard
import com.example.doan_3tuan.ViewModel.NotificationViewModelFirebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavHostController) {
    var viewModel: NotificationViewModelFirebase =
        viewModel(modelClass = NotificationViewModelFirebase::class.java)
    var state = viewModel.state
    var context = LocalContext.current
    var sortByOldest by remember { mutableStateOf(false) }
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
                    actions = {
                        Checkbox(
                            checked = sortByOldest,
                            onCheckedChange = {
                                sortByOldest=it
                                viewModel.sortNotifications(sortByOldest)
                                state = viewModel.state
                            })
                        Text(text = "Cũ nhất")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }},
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B))
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFCBEBF7)),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (state.contactList.count() > 0) {
                items(state.contactList) {notification->
                    Log.d("RenderTest", "Rendering Item: $notification")
                    NotificationCard(notification = notification, onDeleteClick = {
                        viewModel.deleteNotification(notification.id, context)
                        viewModel.refreshNotifications()
                    })
                }
            } else {
                item { Text(text = "NOT LIST") }
            }
        }
    }
}

