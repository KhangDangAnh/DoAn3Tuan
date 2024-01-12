package com.example.doan_3tuan.View.Login

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.doan_3tuan.View.Screen
import com.example.doan_3tuan.ViewModel.AccountViewModel
import com.example.doan_3tuan.ViewModel.DialogSample


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController
) {
    val tfColor = Color(0xFFD9D9D9)
    //màu chữ sau TextField
    val behindTextColor = Color(0xFF61624B)
    //màu chủ đạo
    val mainColor = Color(0xFF07899B)

    var viewModel: AccountViewModel = viewModel(
        modelClass = AccountViewModel::class.java
    )
    var state = viewModel.state
    var idDialog by remember{ mutableStateOf(0)}
    var openDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = mainColor,
                    titleContentColor = Color.White
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                },
            )
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(color = Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 55.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "ĐĂNG KÝ",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.fillMaxWidth(),
                        color = mainColor,
                        fontSize = 35.sp,
                    )
                }

                OutlinedTextField(
                    value = state.email,
                    onValueChange = viewModel::onChangeEmail,
                    label = {
                        Text(text = "Email", textAlign = TextAlign.Center, color = behindTextColor)
                    },
                    shape = RoundedCornerShape(15),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(red = 177, green = 177, blue = 177),
                        unfocusedContainerColor = tfColor
                    ),
                    modifier = Modifier
                        .size(width = 350.dp, height = 60.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = state.password,
                    onValueChange = viewModel::onChangePassword,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    label = {
                        Text(
                            text = "Mật khẩu",
                            textAlign = TextAlign.Center,
                            color = behindTextColor
                        )
                    },
                    shape = RoundedCornerShape(15),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(red = 177, green = 177, blue = 177),
                        unfocusedContainerColor = tfColor
                    ),
                    modifier = Modifier.size(width = 350.dp, height = 60.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = state.rePassword,
                    onValueChange =  viewModel::onChangeRePassword,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    label = {
                        Text(
                            text = "Nhập lại mật khẩu",
                            textAlign = TextAlign.Center,
                            color = behindTextColor
                        )
                    },
                    shape = RoundedCornerShape(15),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(red = 177, green = 177, blue = 177),
                        unfocusedContainerColor = tfColor
                    ),
                    modifier = Modifier.size(width = 350.dp, height = 60.dp)
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        shape = RoundedCornerShape(0),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mainColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.size(width = 350.dp, height = 45.dp),
                        onClick = {
                            if (state.email.isNullOrEmpty() || state.password.isNullOrEmpty()) {
                                idDialog = 1
                                openDialog = true

                            }
                            else if (state.rePassword != state.password) {
                                idDialog = 2
                                openDialog = true

                            }
                            else {
                                viewModel.addUser()
                                if (state.success) {
                                    navController.navigate(Screen.Login.route) {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        }) {
                        Text(text = "Đăng ký", fontSize = 22.sp, fontWeight = FontWeight.Light)
                    }
                }
                TextButton(
                    modifier = Modifier.height(35.dp),
                    onClick = {
                        navController.navigate(Screen.Login.route)
                    }

                ) {
                    Text(text = "Quay lại đăng nhập", color = mainColor)
                }
            }
        }
    }
    if (openDialog) {
        var text: String = ""
        if (idDialog == 1) {
            text = "Mời bạn nhập đầy đủ"
        } else if(idDialog == 2){
            text = "Mật khẩu không trùng khớp"
        }
        DialogSample(
            onDiss = {
                openDialog = false
            },
            onConfirm = {
                openDialog = false
            },
            title = text,
        )
    }
}
