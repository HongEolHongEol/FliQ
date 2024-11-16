package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeTextColorPage(onNextClick: () -> Unit, onColorSelected: (Color) -> Unit) {
    var selectedColor by remember { mutableStateOf<Color?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(120.dp))

            Text(
                text = "배경화면에 어울리는\n  색을 정해볼까요?",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 28.sp,
                modifier = Modifier.padding(bottom = 24.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            // 색상 선택
            val colors = listOf(
                Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.White,
                Color(0xFFFF9800), Color(0xFFE91E63), Color.Gray, Color.Black
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    colors.take(5).forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color)
                                .clickable {
                                    selectedColor = color
                                    onColorSelected(color)
                                }
                                .border(
                                    width = if (selectedColor == color) 3.dp else 1.dp,
                                    color = if (selectedColor == color) Color.Black else Color.LightGray,
                                    shape = CircleShape
                                )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
// 두 번째 줄
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    colors.drop(5).forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color)
                                .clickable {
                                    selectedColor = color
                                    onColorSelected(color)
                                }
                                .border(
                                    width = if (selectedColor == color) 3.dp else 1.dp,
                                    color = if (selectedColor == color) Color.Black else Color.LightGray,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 다음 버튼
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
            ) {
                Text(
                    text = "다음",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }

