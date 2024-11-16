package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R


@Composable
fun SelectBusinessCardStylePage(onNavigateToBusinessCard: () -> Unit, onNavigateToPersonalCard: () -> Unit) {
    val progress = 1.0f
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Progress bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(50))
        ) {
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF9CD2A1)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title text
        Text(
            text = "명함 스타일을 선택해\n보세요.",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Business card selection boxes
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First box: Business card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable { onNavigateToBusinessCard() }, // Navigate to business card page
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "비즈니스 명함",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "기업 명함으로\n 사용하기 좋아요.",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(160.dp) // 고정된 크기 설정
                            .clip(RoundedCornerShape(8.dp)) // 모서리를 둥글게 설정
                            .background(Color.White) // 기본 배경색
                    ) {
                        // Replace with your image
                        Image(
                            painter = painterResource(id = R.drawable.business_card_style),
                            contentDescription = "비즈니스 명함",
                            modifier = Modifier.fillMaxSize(), // 이미지가 박스를 꽉 채우도록 설정
                            contentScale = ContentScale.Fit                        )
                    }
                }
            }

            // Second box: Personal card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable { onNavigateToPersonalCard() }, // Navigate to personal card page
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "개인 명함",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "개성을 담은 나만의 명함을\n 만들어 보세요.",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(160.dp) // 고정된 크기 설정
                            .clip(RoundedCornerShape(8.dp)) // 모서리를 둥글게 설정
                            .background(Color.White) // 기본 배경색
                    ) {
                        // Replace with your image
                        Image(
                            painter = painterResource(id = R.drawable.personal_card_style),
                            contentDescription = "개인 명함",
                            modifier = Modifier.fillMaxSize(), // 이미지가 박스를 꽉 채우도록 설정
                            contentScale = ContentScale.Fit                        )
                    }
                }
            }
        }
    }
}

