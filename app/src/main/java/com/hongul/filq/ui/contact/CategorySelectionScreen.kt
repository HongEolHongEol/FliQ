@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui.contact

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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategorySelectionScreen(
    onCategoryConfirmed: (String) -> Unit // 선택한 카테고리를 전달하는 콜백
) {
    val categories = listOf("개인", "업무") // 카테고리 목록
    val customGreen = Color(0xFF125422) // 커스텀 초록색
    val customGray = Color(0xFFD3D3D3) // 커스텀 회색

    var selectedCategory by remember { mutableStateOf("") } // 선택된 카테고리 상태

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)), // 흐릿한 배경
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(5.dp)) // 흰색 배경 박스
                .padding(24.dp)
                .fillMaxWidth(0.8f), // 가로 크기 조절
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "카테고리 선택",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            categories.forEach { category ->
                Button(
                    onClick = { selectedCategory = category }, // 카테고리 선택
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == category) customGreen else customGray
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = category,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 완료 버튼
            Button(
                onClick = {
                    onCategoryConfirmed(selectedCategory) // 선택한 카테고리 전달
                },
                colors = ButtonDefaults.buttonColors(containerColor = customGreen),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("완료", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySelectionScreenPreview() {
    CategorySelectionScreen(
        onCategoryConfirmed = { category ->
            println("확정된 카테고리: $category")
        }
    )
}
