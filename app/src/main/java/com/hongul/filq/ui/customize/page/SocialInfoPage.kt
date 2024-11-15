package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialInfoPage(onNext: () -> Unit) {
    val progress = 0.7f
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
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "나를 조금 더\n돋보이게 해볼까요?",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Text(
                text = "친구들에게 나를 조금 더 소개해 주세요.",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            val websiteUrl = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "홈페이지",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = websiteUrl.value,
                    onValueChange = { websiteUrl.value = it },
                    placeholder = { Text("홈페이지 주소를 입력해 주세요") },
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

            val MySNS = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "나의 SNS",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), // Ensures Row takes only as much space as needed
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally) // Centers the content horizontally
                ) {
                    // Loop to create 5 circular boxes
                    (1..5).forEach { index ->
                        Box(
                            modifier = Modifier
                                .size(48.dp) // Size of the circle
                                .clip(CircleShape) // Make the box a circle
                                .background(Color(0xFF9CD2A1))
                                .clickable { onNext() } // 클릭 시 onNext 호출
                        ){
                            Text(
                                text = "+",
                                fontSize = 25.sp, // Adjust the font size
                                color = Color(0xFF125422), // Primary dark color for "+"
                                modifier = Modifier.align(Alignment.Center) // Center the "+" symbol inside the circle
                            )
                        }
                    }
                }

                // TextField for user input, with only 5 characters allowed
                OutlinedTextField(
                    value = MySNS.value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 5) { // Ensure only 5 characters can be entered
                            MySNS.value = newValue
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(0.dp) // Hide the input box but still use the value for text input
                        .background(Color.Transparent),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            val tag = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "태그",
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = tag.value,
                    onValueChange = { tag.value = it },
                    placeholder = { Text("예 ) #명함 #디지털명함 #공유") },
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
