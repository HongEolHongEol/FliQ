package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LetterPositionPage(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    var textColor by remember { mutableStateOf(Color.Black) } // 글자 색상 관리
    val selectedBoxes = remember { mutableStateOf(listOf(false, false, false)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f), // 남은 공간을 차지하고 버튼은 하단에 배치
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            repeat(3) { index ->
                BusinessCard(
                    textColor = textColor,
                    alignment = when (index) {
                        0 -> Alignment.BottomStart // 첫 번째 Box는 왼쪽 하단
                        1 -> Alignment.Center       // 두 번째 Box는 가운데
                        else -> Alignment.BottomEnd // 세 번째 Box는 오른쪽 하단
                    },
                    padding = when (index) {
                        0 -> PaddingValues(start = 16.dp) // 왼쪽에 여백 추가
                        1 -> PaddingValues(horizontal = 16.dp) // 양쪽 여백 추가
                        else -> PaddingValues(end = 16.dp, bottom = 8.dp) // 오른쪽 하단 여백 추가
                    },
                    isChecked = selectedBoxes.value[index],
                    onCheckedChange = { checked ->
                        selectedBoxes.value = selectedBoxes.value.toMutableList().apply {
                            this[index] = checked
                        }
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        Button(
            onClick = {
                if (selectedBoxes.value.contains(true)) {
                    onNext() // 선택한 체크박스가 있을 때만 다음 페이지로 이동
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp)
                .align(Alignment.CenterHorizontally) // 버튼을 수평 중앙에 배치
        ) {
            Text(text = "글자 색 바꾸기", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun BusinessCard(textColor: Color,
                 alignment: Alignment,
                 padding: PaddingValues,
                 isChecked: Boolean,
                 onCheckedChange: (Boolean) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(padding)
                .padding(top = 40.dp)
        ) {
            // 글자들이 동일한 위치로 배치되지만 박스에 따라 정렬이 달라짐
            Text(
                text = "홍얼홍얼",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = textColor,
                modifier = Modifier.align(alignment) // alignment에 맞게 글자 위치 조정
                    .offset(y = -70.dp)
            )
            Text(
                text = "캡스톤(1)",
                fontSize = 14.sp,
                color = textColor,
                modifier = Modifier.align(alignment) // alignment에 맞게 글자 위치 조정
                    .offset(y = -50.dp)
            )
            Text(
                text = "010.0000.0000",
                fontSize = 14.sp,
                color = textColor,
                modifier = Modifier.align(alignment) // alignment에 맞게 글자 위치 조정
                    .offset(y = -30.dp)
            )
            Text(
                text = "xxx@stu.kmu.ac.kr",
                fontSize = 14.sp,
                color = textColor,
                modifier = Modifier.align(alignment) // alignment에 맞게 글자 위치 조정
                    .offset(y = -10.dp)
            )
        }

        // 체크박스를 추가하여 선택할 수 있도록 함
        Row(
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked, // 체크 상태를 받음
                onCheckedChange = onCheckedChange // 체크 상태 변경 함수
            )
            Spacer(modifier = Modifier.width(0.dp))
            Text(text = "선택", color = textColor, fontSize = 17.sp)
        }
    }
}