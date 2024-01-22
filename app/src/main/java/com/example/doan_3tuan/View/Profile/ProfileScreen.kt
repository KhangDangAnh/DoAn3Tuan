package com.example.doan_3tuan.QuachVanSang

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.UserData
import com.example.doan_3tuan.R
import com.example.doan_3tuan.View.Component.NavBottomAppBar
import com.example.doan_3tuan.ViewModel.AccountViewModel
import com.example.doan_3tuan.ViewModel.DialogRequireLogin
import com.example.doan_3tuan.ViewModel.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController : NavController,
    userData: UserData?,
    onSignOutClick :() -> Unit,
) {
    val ctx = LocalContext.current
    val viewModel = SettingViewModel(context = ctx)

    val accountViewModel: AccountViewModel = viewModel(modelClass = AccountViewModel::class.java)
    var isLoggedIn by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true){
        accountViewModel.checkLoginStatus()
        isLoggedIn =accountViewModel.isLoggedIn
    }

    var idDialog by remember{ mutableStateOf(0)}
    var openDialog by remember { mutableStateOf(false) }
    val currentUser = FirebaseAuth.getInstance().currentUser
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Cá nhân",
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF07899B),
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
        }, bottomBar = {
            NavBottomAppBar(navController)
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
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                if (!isLoggedIn){
                    Text(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screens.Login.route)
                            },
                        text = "Đăng nhập",
                        fontSize = 20.sp
                    )
                }
                else{
                    if(userData?.email != null){
                        Text(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screens.Login.route)
                                },
                            text = userData.email,
                            fontSize = 20.sp
                        )
                    }
                    else if(currentUser!=null){
                        if(currentUser?.email !=null){
                            Text(
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Screens.Login.route)
                                    },
                                text = currentUser.email!!,
                                fontSize = 20.sp
                            )
                        }
                    }

                }
//                Text(
//                    modifier = Modifier
//                        .clickable {
//                            navController.navigate(Screens.Login.route)
//                        },
//                    text = "Đăng nhập",
//                    fontSize = 20.sp
//                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_bookmark_24),
                    contentDescription = null
                )
                Text(
                    text = "Đã lưu",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            if (!isLoggedIn) {
                                idDialog = 1
                                openDialog = true
                            } else {
                                navController.navigate(NavRoot.luunews.root)
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
                    text = "Cài đặt",
                    color = Color(0xFF07899B),
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
                modifier = Modifier
                    .padding(start = 16.dp, top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_book_24),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { /*Chuyển trang font*/ },
                    text = "Cỡ chữ & Font",
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_tips_and_updates_24),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
                            text = "Giao diện",
                            fontSize = 20.sp
                        )
                    }
                }
                Switch(
                    checked = viewModel.theme,
                    onCheckedChange = {
                        viewModel.theme = it
                        viewModel.saveThemeState(it)
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
                    text = "Dịch vụ",
                    color = Color(0xFF07899B),
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
                    .padding(start = 16.dp, top = 8.dp)
            ) {
                LienHeWithDialog()
            }
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
            ) {
                ChinhSachWithDialog()
            }
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            ) {
                DieuKhoanWithDialog()
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.Gray)
            )
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)) {

                TextButton(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp),
                    onClick = onSignOutClick

                    ){
                    Text(text = "Đăng xuất",
                        fontSize = 20.sp,)
                }
            }
        }
    }

    if (openDialog) {
        var text = ""
        if (idDialog == 1) {
            text = "Hãy đăng nhập để xử dụng chức năng"
        }

        DialogRequireLogin(
            onDiss = {
                openDialog = false
            },
            onConfirm = {
                openDialog = false
                navController.navigate(Screens.Login.route)
            },
            title = text,
        )
    }
}
@Composable
fun LienHeWithDialog() {
    var showDialog by remember { mutableStateOf(false) }
    Text(
        text = "Liên hệ",
        fontSize = 20.sp,
        modifier = Modifier.clickable {
            showDialog = true
        }
    )
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Thông tin liên hệ ") },
            text = { Text("Liên hệ qua số điện thoại: 0816170985 Nguyễn Huỳnh Vũ Hưng ") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "OK")
                }
            }
        )
    }
}

@Composable
fun ChinhSachWithDialog() {
    var showDialog1 by remember { mutableStateOf(false) }
    Text(
        text = "Chính sách bảo mật",
        fontSize = 20.sp,
        modifier = Modifier.clickable {
            showDialog1 = true
        }
    )
    if (showDialog1) {
        AlertDialog(
            onDismissRequest = { showDialog1 = false },
            title = { Text("Chính sách bảo mật") },
            text = {
                LazyColumn {
                    item {
                        Text("Chính sách bảo mật ứng dụng Báo360", fontWeight = FontWeight.Bold)
                        Text("Chào mừng bạn đến với ứng dụng Báo360! Chính sách bảo mật này mô tả cách chúng tôi thu thập, sử dụng và bảo vệ thông tin cá nhân của bạn khi bạn sử dụng ứng dụng của chúng tôi.")
                        Text("1. Thông tin thu thập: ", fontWeight = FontWeight.Bold)
                        Text("Khi bạn tải xuống và sử dụng ứng dụng của chúng tôi, chúng tôi có thể thu thập một số thông tin cá nhân cơ bản như tên, địa chỉ email và ảnh đại diện để tạo tài khoản cá nhân cho bạn.")
                        Text("Chúng tôi cũng có thể thu thập dữ liệu không nhận dạng cá nhân như dữ liệu về sự tương tác với ứng dụng và thiết bị sử dụng.")
                        Text("2. Sử dụng thông tin: ", fontWeight = FontWeight.Bold)
                        Text("Thông tin cá nhân của bạn sẽ được sử dụng để cung cấp các tính năng và dịch vụ trong ứng dụng, ví dụ như tùy chọn cá nhân hóa nội dung và chia sẻ nội dung với bạn bè.")
                        Text("Chúng tôi không bán, chia sẻ hoặc chuyển giao thông tin cá nhân của bạn cho bất kỳ bên thứ ba nào mà không có sự đồng ý của bạn.")
                        Text("3. Bảo mật thông tin: ", fontWeight = FontWeight.Bold)
                        Text("Chúng tôi cam kết bảo vệ thông tin cá nhân của bạn bằng cách triển khai biện pháp bảo mật vững chắc để ngăn chặn mất mát, truy cập trái phép hoặc sử dụng sai mục đích.")
                        Text("Dữ liệu cá nhân của bạn sẽ được lưu trữ an toàn trên máy chủ của chúng tôi và được mã hóa để tăng cường bảo mật.")
                        Text("4. Quyền lợi của Người dùng: ", fontWeight = FontWeight.Bold)
                        Text("Bạn có quyền truy cập, sửa đổi hoặc xóa thông tin cá nhân của mình bất kỳ lúc nào từ cài đặt tài khoản trong ứng dụng.")
                        Text("Chúng tôi cảm ơn sự tin tưởng và sẵn sàng hỗ trợ bạn để đảm bảo trải nghiệm sử dụng ứng dụng Báo360 của bạn là an toàn và thoải mái nhất.")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog1 = false }) {
                    Text(text = "OK")
                }
            }
        )
    }
}

@Composable
fun DieuKhoanWithDialog() {
    var showDialog2 by remember { mutableStateOf(false) }
    Text(
        text = "Điều khoản sử dụng",
        fontSize = 20.sp,
        modifier = Modifier.clickable {
            showDialog2 = true
        }
    )
    if (showDialog2) {
        AlertDialog(
            onDismissRequest = { showDialog2 = false },
            title = { Text("Điều khoản sử dụng") },
            text = {
                LazyColumn {
                    item {
                        Text("Điều khoản sử dụng ứng dụng Báo360", fontWeight = FontWeight.Bold)
                        Text("Chào mừng bạn đến với ứng dụng đọc báo của chúng tôi! Trước khi bạn bắt đầu sử dụng ứng dụng, vui lòng đọc và hiểu rõ những điều khoản sau đây:")
                        Text("1. Tài khoản Người dùng:")
                        Text("Bạn cần phải đăng ký một tài khoản để sử dụng đầy đủ tính năng của ứng dụng.")
                        Text("Bảo mật thông tin tài khoản là trách nhiệm của bạn. Không chia sẻ thông tin đăng nhập với người khác.")
                        Text("2. Nội dung:")
                        Text("Các bài báo, hình ảnh, và nội dung khác trên ứng dụng là tài sản của chúng tôi hoặc các đối tác cấp phép.")
                        Text("Bạn cam kết không sao chép, phát tán, hoặc sử dụng mục đích thương mại nội dung mà không có sự cho phép.")
                        Text("3. Bản quyền:")
                        Text("Tất cả các quyền sở hữu trí tuệ đều thuộc sở hữu của chúng tôi hoặc bên cấp phép. Sử dụng ứng dụng này không đồng nghĩa với việc bạn có quyền sở hữu bất kỳ nội dung nào từ ứng dụng.")
                        Text("4. Bảo mật và Quyền riêng tư: ")
                        Text("Chúng tôi cam kết bảo vệ thông tin cá nhân của bạn theo quy định của chính sách quyền riêng tư.")
                        Text("Bạn có thể xem chi tiết về cách chúng tôi thu thập, lưu trữ và sử dụng thông tin trong Chính Sách Quyền Riêng Tư của chúng tôi.")
                        Text("5. Tương tác Người dùng: ")
                        Text("Chúng tôi khuyến khích sự tương tác tích cực từ người dùng. Tuy nhiên, chúng tôi có quyền loại bỏ bất kỳ nội dung không phù hợp hoặc vi phạm quy định này.")
                        Text("6. Cập nhật ứng dụng: ")
                        Text("Để đảm bảo trải nghiệm tốt nhất, bạn cần cập nhật ứng dụng đến phiên bản mới nhất khi có sẵn.")
                        Text("Bằng cách sử dụng ứng dụng của chúng tôi, bạn đồng ý tuân theo những điều khoản và điều kiện này. Chúng tôi có quyền thay đổi điều khoản mà không cần thông báo trước. Hãy kiểm tra định kỳ để cập nhật với những thay đổi mới.")
                        Text("Cảm ơn bạn đã sử dụng ứng dụng đọc báo của chúng tôi!")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog2 = false }) {
                    Text(text = "OK")
                }
            }
        )
    }
}
