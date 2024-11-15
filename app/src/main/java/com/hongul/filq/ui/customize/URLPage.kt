package com.hongul.filq.ui.customize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun URLPage(
    title: String,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
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
                onClick = onRegisterClick,
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

// Facebook URL 페이지
@Composable
fun FaceBookURLPage() {
    URLPage(
        title = "Facebook",
        onBackClick = { /* 뒤로 가기 동작 */ },
        onRegisterClick = { /* Facebook URL 등록 동작 */ }
    )
}

// Instagram URL 페이지
@Composable
fun InstaGramURLPage() {
    URLPage(
        title = "Instagram",
        onBackClick = { /* 뒤로 가기 동작 */ },
        onRegisterClick = { /* Instagram URL 등록 동작 */ }
    )
}
