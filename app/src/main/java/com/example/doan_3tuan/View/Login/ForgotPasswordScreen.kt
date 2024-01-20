package com.example.doan_3tuan.View.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.doan_3tuan.Model.User
import com.example.doan_3tuan.Model.UserData
import com.example.doan_3tuan.ViewModel.AccountViewModel
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    userData: UserData?,
    onSignOutClick :() -> Unit,


    ){
    val tfColor = Color(0xFFD9D9D9)
    //màu chữ sau TextField
    val behindTextColor = Color(0xFF61624B)
    //màu chủ đạo
    val mainColor = Color(0xFF07899B)
    //màu chữ giới thiệu
    val guideTextColor = Color(0xFF8BC9D2)
    val viewModel: AccountViewModel = viewModel(
        modelClass = AccountViewModel::class.java
    )
    //Trạng thái tài khoản
    val state = viewModel.state
    var idUser by remember{ mutableStateOf("")}
    val currentUser = FirebaseAuth.getInstance().currentUser

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = mainColor, titleContentColor = Color.White),
                title = {},
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "", tint = Color.Black)
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
            if (userData?.profilePictureUrl != null) {
                AsyncImage(model = userData.profilePictureUrl, contentDescription = null)
            }

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
                    if(currentUser!=null){
                        if(currentUser.email!=null){
                        viewModel.getID()
                        }
                        idUser = viewModel.idUser
                        Text(
                            text = idUser,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.fillMaxWidth(),
                            color = mainColor,
                            fontSize = 35.sp,
                        )
                    }
//                    if(userData?.userId!=null){
//                        Text(
//                            text = userData.userId,
//                            textAlign = TextAlign.Center,
//                            fontWeight = FontWeight.Light,
//                            modifier = Modifier.fillMaxWidth(),
//                            color = mainColor,
//                            fontSize = 35.sp,
//                        )
//                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Quên mật khẩu ư?", fontSize = 16.sp, color = guideTextColor)
                Text(text = "Đừng lo lắng", fontSize = 16.sp, color = guideTextColor)
                Text(
                    text = "Chúng tôi sẽ giúp bạn tìm lại tài khoản",
                    fontSize = 16.sp,
                    color = guideTextColor
                )
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

                Spacer(modifier = Modifier.padding(15.dp))
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
                        onClick = onSignOutClick
                    ) {
                        Text(text = "Log out", fontSize = 22.sp, fontWeight = FontWeight.Light)
                    }
                }
            }
        }
    }
}