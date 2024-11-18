package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BusinessCardPreviewPage( backgroundRes: Int, onCompleteClick: () -> Unit, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // 전체 화면에 패딩 추가
        verticalArrangement = Arrangement.SpaceBetween // 위, 아래로 요소 정렬
    ) {
        // 상단 명함 미리보기
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // 화면의 적절한 공간 차지
                .align(Alignment.CenterHorizontally), // 중앙 정렬
            contentAlignment = Alignment.Center
        ) {
            // 배경 이미지
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = "Template Background",
                contentScale = ContentScale.Fit, // 전체 크기를 맞춤
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // 더 큰 크기로 설정
            )
        }

// 하단 버튼
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 하단 버튼
            Button(
                onClick = onCompleteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
            ) {
                Text(
                    text = "완료",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}