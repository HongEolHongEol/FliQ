package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBusinessCardPage(onCompleteClick: () -> Unit) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            // 중간 이미지
            Image(
                painter = painterResource(id = R.drawable.bc4), // 이미지 리소스 변경 필요
                contentDescription = "명함 이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // 원하는 높이 설정
            )

            Spacer(modifier = Modifier.weight(1f))

            // 완료 버튼
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

