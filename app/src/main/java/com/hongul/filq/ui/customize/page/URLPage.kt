package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun URLPage(
    title: String,
    onBack: () -> Unit,
    onRegisterClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center // 중앙 정렬
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.SemiBold, // 굵게 설정
                            fontSize = 18.sp, // 원하는 크기로 설정
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 121.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            val url = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = url.value,
                    onValueChange = { url.value = it },
                    placeholder = { Text("SNS 주소를 입력하세요.") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.Gray.copy(alpha = 0.1f)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
                Text(
                    text = "${url.value.length}/100",
                    modifier = Modifier.align(Alignment.End),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 등록 버튼
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { onRegisterClick(url.value) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
                ) {
                    Text(text = "등록", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

//// Facebook URL 페이지
//@Composable
//fun FaceBookURLPage(
//    onBackClick: () -> Unit,
//    onRegisterClick: () -> Unit
//) {
//    URLPage(
//        title = "Facebook",
//        onBackClick = onBackClick,
//        onRegisterClick = onRegisterClick
//    )
//}
//
//// Instagram URL 페이지
//@Composable
//fun InstaGramURLPage(
//    onBackClick: () -> Unit,
//    onRegisterClick: () -> Unit
//) {
//    URLPage(
//        title = "Instagram",
//        onBackClick = onBackClick,
//        onRegisterClick = onRegisterClick
//    )
//}
//
//@Composable
//fun XURLPage(
//    onBackClick: () -> Unit,
//    onRegisterClick: () -> Unit
//) {
//    URLPage(
//        title = "X",
//        onBackClick = onBackClick,
//        onRegisterClick = onRegisterClick
//    )
//}
//
//@Composable
//fun YoutubeURLPage(
//    onBackClick: () -> Unit,
//    onRegisterClick: () -> Unit
//) {
//    URLPage(
//        title = "Youtube",
//        onBackClick = onBackClick,
//        onRegisterClick = onRegisterClick
//    )
//}