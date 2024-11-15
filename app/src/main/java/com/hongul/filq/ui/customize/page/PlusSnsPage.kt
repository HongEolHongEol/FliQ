package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlusSnsPage(
    onBack: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "추가하고 싶은 SNS를\n선택하세요.",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val MySNS = remember { mutableStateOf("") }

            Column(modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), // Ensures Row takes only as much space as needed
                    horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally) // Centers the content horizontally
                ) {
                    // snsList와 imageResources 리스트 생성
                    val snsList = listOf("Facebook", "Instagram", "X", "YouTube")
                    val imageResources = listOf(
                        R.drawable.logo_facebook,
                        R.drawable.logo_instagram,
                        R.drawable.logo_x,
                        R.drawable.logo_youtube,
                    )

                    // snsList를 순회하여 각 SNS와 이미지를 표시
                    snsList.indices.forEach { index ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // 각 원 아래에 텍스트를 중앙 정렬
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp) // 원의 크기 설정
                                    .clip(CircleShape) // 원 모양으로 클립
                                    .background(Color.White) // 배경을 흰색으로 설정
                                    .border(1.dp, Color.Gray, CircleShape) // 회색 테두리 설정
                            ) {
                                // 이미지 리소스를 불러와서 원 안에 맞게 표시
                                Image(
                                    painter = painterResource(id = imageResources[index]),
                                    contentDescription = snsList[index], // 접근성을 위한 설명 추가
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp)) // 원과 텍스트 사이에 간격 추가
                            Text(
                                text = snsList[index], // SNS 이름 (Facebook, YouTube 등)
                                fontSize = 12.sp, // 폰트 크기 설정
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Center
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
                        .padding(vertical = 15.dp)
                        .height(0.dp) // Hide the input box but still use the value for text input
                        .background(Color.Transparent),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

        }
    }


