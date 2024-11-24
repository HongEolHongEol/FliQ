@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.ui.theme.FilQTheme
import com.hongul.filq.R
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun BusinessCardModifyScreen(
    onComplete: (List<Pair<Int, String>>) -> Unit
) {
    val titleColor = Color(0xFF125422) // 초록색 제목 색상

    // 초기 데이터
    var items by remember {
        mutableStateOf(
            listOf(
                R.drawable.ic_user to "홍알홍알",
                R.drawable.ic_group to "캡스톤(1)",
                R.drawable.ic_call to "01057540000",
                R.drawable.ic_groupname to "홍얼홍얼",
                R.drawable.ic_role to "백엔드개발자",
                R.drawable.ic_email to "xxx@stu.kmu.ac.kr"
            )
        )
    }
    // SNS
    val snsItems = listOf(
        R.drawable.ic_insta to "@hongeol.hongeol",
        R.drawable.ic_kakao to "hongeol",
        R.drawable.ic_facebook to "honghonghong",
        R.drawable.ic_twitter to "@hongeol",
        R.drawable.ic_blog to "hongeoul"
    )

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
                // 일반 항목 출력
                items(items) { item ->
                    var textState by remember { mutableStateOf(item.second) }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        // 제목 텍스트
                        Text(
                            text = when (item.first) {
                                R.drawable.ic_user -> "이름 또는 닉네임"
                                R.drawable.ic_group -> "명함 제목"
                                R.drawable.ic_call -> "휴대폰 번호"
                                R.drawable.ic_groupname -> "기업 또는 단체명"
                                R.drawable.ic_role -> "부서/직책"
                                R.drawable.ic_email -> "이메일"
                                else -> ""
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = titleColor,
                            modifier = Modifier
                                .padding(start = 8.dp, bottom = 4.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            // 아이콘
                            Image(
                                painter = painterResource(id = item.first),
                                contentDescription = null,
                                modifier = Modifier.size(27.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))

                            // 수정 가능
                            TextField(
                                value = textState,
                                onValueChange = { newValue ->
                                    textState = newValue
                                    items = items.map { if (it == item) item.first to newValue else it }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = titleColor,
                                    unfocusedIndicatorColor = Color.LightGray 
                                )
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "SNS",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = titleColor,
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 8.dp)
                    )
                }

                items(snsItems) { snsItem ->
                    var textState by remember { mutableStateOf(snsItem.second) }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = snsItem.first),
                            contentDescription = null,
                            modifier = Modifier.size(27.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))

                        // 수정 가능
                        TextField(
                            value = textState,
                            onValueChange = { newValue ->
                                textState = newValue
                                snsItems.map { if (it == snsItem) snsItem.first to newValue else it }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = titleColor,
                                unfocusedIndicatorColor = Color.LightGray
                            )
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onComplete(items + snsItems) },
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

@Preview(showBackground = true)
@Composable
fun BusinessCardModifyScreenPreview() {
    FilQTheme {
        BusinessCardModifyScreen(onComplete = {})
    }
}
