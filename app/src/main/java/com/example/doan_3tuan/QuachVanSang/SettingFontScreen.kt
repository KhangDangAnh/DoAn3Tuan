@file:Suppress("UNUSED_EXPRESSION")

package com.example.doan_3tuan.QuachVanSang

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doan_3tuan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingFontScreen() {
    val ctx = LocalContext.current
    val viewModel = SettingViewModel(context = ctx)
    var inriaSansFontFamily by remember { mutableStateOf(FontFamily(Font(R.font.inriasans))) }
    var arialFontFamily by remember { mutableStateOf(FontFamily(Font(R.font.arial))) }
    var styleFont = if (viewModel.font) inriaSansFontFamily else arialFontFamily
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Cỡ chữ & Font",
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Font chữ:",
                    fontSize = 20.sp,
                    fontFamily = styleFont
                )

                var expanded by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
                        .clickable {
                            expanded = !expanded
                        }
                ) {
                    Text(
                        fontFamily = styleFont,
                        text = if(viewModel.font) "Inria Sans" else "Arial",
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color.Gray)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Inria Sans", color = Color.White) },
                            onClick = {
                                viewModel.saveFontState(true,"Inria Sans")
                                expanded = false
                            },
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Arial", color = Color.White) },
                            onClick = {
                                viewModel.saveFontState(false, "Arial")
                                expanded = false
                            },
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .absoluteOffset(x = 170.dp, y = 8.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    fontFamily = styleFont,
                    text = "Cỡ chữ: ",
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Nhỏ", fontSize = 16.sp, fontFamily = styleFont)
                Spacer(modifier = Modifier.padding(end = 5.dp))
                Switch(
                    checked = viewModel.large,
                    onCheckedChange = {
                        viewModel.large = it
                        viewModel.saveLargeState(it)
                    },
                    thumbContent = {
                        if (viewModel.large) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_circle_24),
                                contentDescription = ""
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_circle_24),
                                contentDescription = ""
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(start = 5.dp))
                Text(text = "Lớn", fontSize = 20.sp, fontFamily = styleFont)
            }
            Divider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                text = "Lịch nghỉ Tết Âm lịch 2024 của học sinh Hà Nội như sau: Theo thông tin mới nhất thì các" +
                        " ngày nghỉ lễ, tết được thực hiện theo quy định của Luật lao động và các văn bản hướng dẫn" +
                        " hàng năm. Như vậy lịch nghỉ Tết Nguyên đán 2024 của học sinh Hà Nội sẽ bắt đầu từ ngày " +
                        "8-14/2/2024 (tức 29 tháng Chạp năm Quý Mão đến hết mùng 5 tháng Giêng năm Giáp Thìn).",
                fontSize = if (viewModel.large) 20.sp else 16.sp,
                fontFamily = styleFont
            )
        }
    }
}