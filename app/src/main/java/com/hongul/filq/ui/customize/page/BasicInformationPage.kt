package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicInformationPage(onNext: () -> Unit) {
    val progress = 0.25f

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
                text = "기본 정보를\n입력해 주세요.",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            // 이름 또는 닉네임 입력 필드
            val name = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "이름 또는 닉네임 *",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                // 이름 입력 필드
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    placeholder = { Text("2-20자 이내 / 특수문자 사용 가능") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.Gray.copy(alpha = 0.1f)), // 배경 색상 설정
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent, // 테두리 없애기
                        unfocusedBorderColor = Color.Transparent // 테두리 없애기
                    )
                )

                // "0/20" 문자
                Text(
                    text = "${name.value.length}/20",
                    modifier = Modifier.padding(start = 320.dp, top = 0.dp),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp, color = Color.Gray)
                )
                Text(
                    text = "*은 필수 사항입니다.",
                    modifier = Modifier.padding(start = 240.dp, top = 0.dp),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 명함 제목 입력 필드
            val title = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "명함 제목",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    placeholder = { Text("명함 제목을 입력하세요") },
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

            // 휴대폰 번호 입력 필드
            val phone = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "휴대폰 번호",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = phone.value,
                    onValueChange = { phone.value = it },
                    placeholder = { Text("휴대폰 번호를 입력하세요") },
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


            // 이메일 입력 필드
            val email = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "이메일",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    placeholder = { Text("이메일을 입력하세요") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

            // 주소 입력 필드
            val address = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "주소",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = address.value,
                    onValueChange = { address.value = it },
                    placeholder = { Text("주소를 입력하세요") },
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


            Spacer(modifier = Modifier.height(24.dp))

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

