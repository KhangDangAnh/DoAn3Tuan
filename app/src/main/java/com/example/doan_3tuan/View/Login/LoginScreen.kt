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
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.doan_3tuan.Model.SignInState
import com.example.doan_3tuan.ViewModel.Screens
import com.example.doan_3tuan.ViewModel.AccountViewModel
import com.example.doan_3tuan.ViewModel.DialogErrorLogin


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(
    navController: NavHostController,
    stateGoogle : SignInState,
    onSignInClick: ()->Unit
){
    //màu TextField
    val tfColor = Color(0xFFD9D9D9)
    //màu chữ sau TextField
    val behindTextColor = Color(0xFF61624B)
    //màu chủ đạo
    val mainColor = Color(0xFF07899B)
    val viewModel: AccountViewModel = viewModel(
        modelClass = AccountViewModel::class.java
    )
    //Trạng thái tài khoản
    val state = viewModel.state

    //Kiểm tra và xuất thông báo
    var idDialog by remember{ mutableIntStateOf(0) }
    var openDialog by remember { mutableStateOf(false) }

    var loading by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = mainColor, titleContentColor = Color.White),
                title = {},
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
                        onClick = {
                            navController.navigate(Screens.HomeScreen.route)
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
    ){
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
                        text = "ĐĂNG NHẬP",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.fillMaxWidth(),
                        color =  mainColor,
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
                        Text(text = "Mật khẩu", textAlign = TextAlign.Center, color = behindTextColor)
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
                        colors = ButtonDefaults.buttonColors(containerColor = mainColor, contentColor = Color.White),
                        modifier = Modifier.size(width = 350.dp, height = 45.dp),
                        onClick = {

                            loading = true

                            if (state.email.isNullOrEmpty() || state.password.isNullOrEmpty()) {
                                idDialog = 1
                                openDialog = true
                            }
                            else{
                                viewModel.signIn()
                                if (!state.success) {
                                    idDialog =2
                                    openDialog =true
                                }
                                else {

                                    state.success
                                }
                            }

                        },

                    ) {
                        Text(text="Đăng Nhập", fontSize = 22.sp, fontWeight = FontWeight.Light)
                    }
                }
                Spacer(modifier = Modifier.padding(25.dp))
                Text(text = "Hoặc đăng nhập với tài khoản", fontSize = 12.sp)
                GoogleCard(
                    state = stateGoogle,
                    onSignInClick = onSignInClick
                )
                Spacer(Modifier.padding(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        modifier = Modifier.height(30.dp),
                        onClick = {
                            navController.navigate(Screens.Register.route)
                        }
                    ) {
                        Text(
                            text = "Bạn chưa có tài khoản ?",
                            fontSize = 12.sp,
                            color = mainColor,
                        )
                    }
                }
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(25.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
        }
    }

    //Xử lý thông báo
    if (openDialog) {
        var text= ""
        if (idDialog == 1) {
            text = "Mời bạn nhập đầy đủ"
        }
//        else if(idDialog == 2){
//            text = "Đăng nhập thật bại, vui lòng thử lại"
//        }
        DialogErrorLogin(
            onDiss = {
                openDialog = false
                idDialog = 0
                loading =false
            },
            onConfirm = {
                openDialog = false
                idDialog = 0
                loading =false
            },
            title = text,
        )
    }
}