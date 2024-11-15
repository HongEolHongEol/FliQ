package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationInfoPage(onNext: () -> Unit) {
    val titlePadding = 121.dp
    val progress = 0.5f
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(50)) // Box의 모서리를 둥글게 설정
        ) {
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxSize() // Box 안에 맞게 채움
                    .clip(RoundedCornerShape(50)), // 진행 바도 둥글게 설정
                color = Color(0xFF9CD2A1) // 진행 바 색상
            )
        }

        Text(
            text = "나의 소속을\n입력해 주세요.",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        val organizationName = remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "기업 또는 단체명",
                modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )

            // 기업 또는 단체명 입력 필드
            OutlinedTextField(
                value = organizationName.value,
                onValueChange = { organizationName.value = it },
                placeholder = { Text("예) 계명대학교") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.Gray.copy(alpha = 0.1f)), // 배경 색상 설정
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent, // 테두리 없애기
                    unfocusedBorderColor = Color.Transparent // 테두리 없애기
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        val departmentPosition = remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "부서 / 직책",
                modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )

            // 부서 / 직책 입력 필드
            OutlinedTextField(
                value = departmentPosition.value,
                onValueChange = { departmentPosition.value = it },
                placeholder = { Text("예 ) 컴퓨터공학과 / 5723483") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.Gray.copy(alpha = 0.1f)), // 배경 색상 설정
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent, // 테두리 없애기
                    unfocusedBorderColor = Color.Transparent // 테두리 없애기
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        val additionalPosition = remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "추가 직책",
                modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = additionalPosition.value,
                onValueChange = { additionalPosition.value = it },
                placeholder = { Text("예 ) 기획부장") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.Gray.copy(alpha = 0.1f)), // 배경 색상 설정
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent, // 테두리 없애기
                    unfocusedBorderColor = Color.Transparent // 테두리 없애기
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        // 다음 버튼
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter // 버튼을 화면 하단에 정렬
        ) {
            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
            ) {
                Text(text = "다음", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

