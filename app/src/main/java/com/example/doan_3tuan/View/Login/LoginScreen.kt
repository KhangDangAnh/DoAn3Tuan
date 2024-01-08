package com.example.doan_3tuan.View.Login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doan_3tuan.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(

){
    //màu TextField
    val tfColor = Color(red = 217, green = 217, blue = 217)
    //màu chữ sau TextField
    val behindTextColor = Color(red = 161, green = 163, blue = 139)
    //màu chủ đạo
    val mainColor = Color(red = 7, green = 137, blue = 155)

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = mainColor, titleContentColor = Color.White),
                title = {},
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
                    value = "",
                    onValueChange = {},
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
                    value = "",
                    onValueChange = {},
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

                        }) {
                        Text(text="Đăng Nhập", fontSize = 22.sp, fontWeight = FontWeight.Light)
                    }
                }
                Spacer(modifier = Modifier.padding(25.dp))
                Text(text = "Hoặc bạn có thể đăng nhập với tài khoản", fontSize = 12.sp)
                Card(
                    modifier = Modifier.fillMaxWidth().height(45.dp),
                    onClick = {},
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(width = 1.5.dp, color = Color.Black)
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),

                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.gg_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(33.dp)

                        )
                        Text(
                            text = "Sign in with Google",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(5.dp)

                        )
                    }
                }
                Spacer(Modifier.padding(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        modifier = Modifier.height(30.dp),
                        onClick = {

                        }
                    ) {
                        Text(
                            text = "Quên mật khẩu ?",
                            fontSize = 12.sp,
                            color = mainColor
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        modifier = Modifier.height(30.dp),
                        onClick = {

                        }
                    ) {
                        Text(
                            text = "Bạn chưa có tài khoản ?",
                            fontSize = 12.sp,
                            color = mainColor
                        )
                    }
                }
            }
        }
    }
}
