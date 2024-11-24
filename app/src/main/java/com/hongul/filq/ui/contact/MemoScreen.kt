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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.ui.theme.FilQTheme

@Composable
fun MemoScreen(
    initialMemo: String,
    onSave: (String) -> Unit
) {
    val customColor = Color(0xFF125422)
    var memo by remember { mutableStateOf(initialMemo) }

    // 전체 호마면 설정
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center // 화면 중앙
    ) {
        // 메모 박스
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "FliQ 메모",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = customColor
                    )
                }

                Button(
                    onClick = { onSave(memo) },
                    colors = ButtonDefaults.buttonColors(containerColor = customColor),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Text("저장", color = Color.White)
                }
            }

            TextField(
                value = memo,
                onValueChange = { memo = it },
                modifier = Modifier
                    .fillMaxSize(),
                placeholder = { Text("메모를 입력하세요...") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = customColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoScreenPreview() {
    MemoScreen(
        initialMemo = "이 메모는 미리보기입니다.\n수정해보세요!",
        onSave = { updatedMemo ->
            println("저장된 메모: ")
        }
    )
}
