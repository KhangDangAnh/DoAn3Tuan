package com.example.doan_3tuan.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chitiet_Screen(navController: NavHostController) {
    val news = listOf(
        kotlin.Pair(R.drawable.img, "News"),
        kotlin.Pair(R.drawable.img, "News"),
        kotlin.Pair(R.drawable.img, "News"),
        kotlin.Pair(R.drawable.img, "News"),
        kotlin.Pair(R.drawable.img, "News"),
        kotlin.Pair(R.drawable.img, "News"),
        kotlin.Pair(R.drawable.img, "News"),
        kotlin.Pair(R.drawable.img, "News")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B)),
            )
        },
        bottomBar = {
            BottomAppBar(actions = {
                IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.baseline_bookmark_24), contentDescription = "")
                }
            })
        }
    ) {
        LazyColumn(Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            item{
                Text(text = "Ốc Thanh Vân định cư Úc," +
                        "số tiền dành cho con gái Mai Phương ra sao?", fontWeight = FontWeight.Bold)
                Text(text = "27/10/2013")
                Text(text = "(VTC News) - Ốc Thanh Vân cùng gia đình" +
                        " định cư tại Úc, trước khi đi nữ nghệ sĩ đã" +
                        " phải xử lý ra sao khi cùng đứng tên trong sổ " +
                        " tiết kiệm số tiền dành cho bé Lavie.")
                Image(painter = painterResource(id = R.drawable.img), contentDescription = "")
                Text(text = "Theo nguồn tin riêng của VTC News, nghệ " +
                        "sĩ Ốc Thanh Vân và gia đình đã hoàn tất thủ " +
                        "tục và bay sang Úc định cư từ tối 25/12. " +
                        "Mới đây nữ nghệ sĩ đã đăng tải khoảng khắc " +
                        "cả gia đình đón năm mới tại \"xứ sở chuột túi\".\n" +
                        "Trước khi định cư, Ốc Thanh Vân lo lắng việc" +
                        " này sẽ gây tranh cãi khi vẫn đứng tên trên sổ" +
                        " tiết kiệm dành cho con gái Mai Phương nên đã " +
                        "hoàn tất các thủ tục bàn giao lại sổ tiết kiệm.\n" +
                        "Nguồn tin tiết lộ, sổ tiết kiệm trước đó do Ốc " +
                        "Thanh Vân và Trương Bảo Như đứng tên dưới sự làm" +
                        " chứng của Trấn Thành nay đã được bàn giao lại cho " +
                        "Trương Bảo Như và người chăm sóc bé Lavie.")
                Image(painter = painterResource(id = R.drawable.img), contentDescription = "")
                Text(text = "Bé Lavie tên thật là Phùng Ngọc Thiên Như," +
                        " sinh năm 2013. Sau khi Mai Phương qua đời, bé " +
                        "sống với chị Mi Châu - người chăm sóc bé từ khi" +
                        " lọt lòng cho đến hiện tại. Trên trang cá nhân " +
                        "chị Mi Châu thường xuyên đăng tải các clip về " +
                        "cuộc sống thường ngày của bé Lavie để mọi người " +
                        "tiện theo dõi. Không chỉ chăm sóc cuộc sống thường " +
                        "ngày cho bé Lavie, chị Mi Châu còn cho bé tham gia các " +
                        "chuyến đi thiện nguyện và lớp học kỹ năng để bé tự tin, " +
                        "dễ thích nghi với cuộc sống hơn.")
            }
            item{
                Divider()
                Text(text = "Tin khác", fontWeight = FontWeight.Bold)
            }
            items(news)
            {
                Divider()
                News_Card(
                    img = it.first,
                    title = it.second,
                    Click = { navController.navigate(NavRoot.chitiet.root) })
            }
        }
    }
}