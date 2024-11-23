@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DeleteFriendCard(
    name: String, // 삭제 대상 이름
    onConfirm: () -> Unit, // 삭제 확인 콜백
    onCancel: () -> Unit // 삭제 취소 콜백
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(5.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "홍츄핑을 정말로 삭제하시겠습니까?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color(0xFF125422) // 커스텀 초록색
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3)), // 회색
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("취소", color = Color.Black)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)), // 초록색
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("삭제", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteFriendCardPreview() {
    DeleteFriendCard(
        name = "홍츄핑",
        onConfirm = { println("삭제 확인") },
        onCancel = { println("삭제 취소") }
    )
}
