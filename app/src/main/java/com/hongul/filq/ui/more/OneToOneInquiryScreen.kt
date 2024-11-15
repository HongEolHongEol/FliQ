@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OneToOneInquiryScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "1:1 문의",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "뒤로 가기")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // 이메일 입력 영역
            Text(
                text = "연락 받을 이메일 주소",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
            BasicTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCCCCCC))
                    .padding(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 이메일 예시와 안내 문구
            Text(
                text = "예) xxx@stu.kmu.ac.kr",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light),
                color = Color(0xFF6F6F6F)
            )
            Text(
                text = "이메일 주소는 [마이페이지]>[계정 설정]에서 변경할 수 있습니다.",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light),
                color = Color(0xFF6F6F6F)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 문의 내용 입력 영역
            Text(
                text = "문의 내용",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = Color.Black
            )
            BasicTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFCCCCCC))
                    .padding(12.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 파일 첨부 텍스트
            Text(
                text = "+파일 추가",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light),
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 문의하기 버튼
            Button(
                onClick = { /* 문의하기 로직 */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD8D8D8))
            ) {
                Text(
                    text = "문의하기",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOneToOneInquiryScreen() {
    OneToOneInquiryScreen(onBack = {})
}
