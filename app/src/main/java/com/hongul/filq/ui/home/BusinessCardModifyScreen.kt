package com.hongul.filq.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.ui.theme.FilQTheme
import com.hongul.filq.R
import kotlinx.serialization.Serializable
import androidx.compose.foundation.shape.RoundedCornerShape

@Serializable
data class BusinessCardModifyRoute(
    val cardId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardModifyScreen(onComplete: () -> Unit = {}) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("명함 편집", fontSize = 20.sp) }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item { Spacer(modifier = Modifier.height(23.dp)) }

                // 수정 ㄱㄴ 목록 리이이ㅣ이이스트
                items(listOf(
                    R.drawable.ic_person to "홍알홍알",
                    R.drawable.ic_group to "캡스톤(1)",
                    R.drawable.ic_star to "홍얼홍얼",
                    R.drawable.ic_mic to "010 0000 0000",
                    R.drawable.ic_pencil to "비지니스명함",
                    R.drawable.ic_pencil to "xxx@stu.kmu.ac.kr",
                    R.drawable.ic_link to "@hongeol.hongeol",
                    R.drawable.ic_link to "hong",
                    R.drawable.ic_link to "//fliq",
                    R.drawable.ic_link to "http://digital.profile",
                    R.drawable.ic_link to "eofk-dfdr"
                )) { (icon, initialText) ->
                    BusinessCardItemEditable(icon = icon, initialText = initialText)
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                // 완료 버튼
                item {
                    Button(
                        onClick = onComplete,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = "완료",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCardItemEditable(icon: Int, initialText: String) {
    var textState by remember { mutableStateOf(initialText) } // 상태를 관리하여 텍스트 필드에 표시

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 11.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(27.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))

        // 수정 가능거----> TextField
        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModifyBusinessCardScreenEditPreview() {
    FilQTheme {
        BusinessCardModifyScreen()
    }
}
