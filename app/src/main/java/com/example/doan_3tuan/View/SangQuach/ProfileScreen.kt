package com.example.doan_3tuan.View.SangQuach

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doan_3tuan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(){
    var checked by remember { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Đã lưu",
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { /*Quay về*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                ) {
                    Text(
                        text = "text",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Đăng nhập",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* Lưu */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bookmark_24),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Đã lưu",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.Gray)
            )
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = "Cài đặt",
                    color = Color.Blue,
                    fontSize = 20.sp
                )
            }
            Divider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* Lưu */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_menu_book_24),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Cỡ chữ & Font",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /* Lưu */ }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_tips_and_updates_24),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = "Giao diện",
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    }
                }
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    thumbContent = {
                        if (checked) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = ""
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.Gray)
            )
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = "Sản Phẩm",
                    color = Color.Blue,
                    fontSize = 20.sp
                )
            }
            Divider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            )
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 7.dp)
            ) {
                Text(
                    text = "Liên hệ",
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 7.dp)
            ) {
                Text(
                    text = "Chính sách bảo mật",
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 7.dp)
            ) {
                Text(
                    text = "Điều khoản sử dụng",
                    fontSize = 20.sp
                )
            }
        }
    }
}