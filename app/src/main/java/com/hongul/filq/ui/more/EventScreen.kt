@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment

// 이벤트/공지사항 화면
@Composable
fun EventScreen(onBack: () -> Unit, onNoticeClick: () -> Unit) {
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("이벤트/공지사항") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "뒤로 가기")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = selectedTabIndex.value == 0,
                    onClick = { selectedTabIndex.value = 0 },
                    text = { Text("이벤트") },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray
                )
                Tab(
                    selected = selectedTabIndex.value == 1,
                    onClick = { selectedTabIndex.value = 1 },
                    text = { Text("공지사항") },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            if (selectedTabIndex.value == 0) {
                EventContent()
            } else {
                NoticeContent(onNoticeClick = onNoticeClick)
            }
        }
    }
}

// 이벤트 내용 컴포저블
@Composable
fun EventContent() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { /* 클릭 시 동작 추가 가능 */ },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "캡스톤 A+ 받기 이벤트",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ">",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
        Text(
            text = "2024-09-01 14:30 ~ 2024-12-25 14:30",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 0.1.dp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

// 공지사항 내용 컴포저블
@Composable
fun NoticeContent(onNoticeClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onNoticeClick() }, // 클릭 시 상세 화면으로 이동
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "[업데이트] 앱 업데이트가 완료되었습니다.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ">",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
        Text(
            text = "2024-09-10 14:30",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 0.1.dp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

// 공지사항 상세 화면
@Composable
fun NoticeDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("이벤트/공지사항") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "뒤로 가기")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "[업데이트] 앱 업데이트가 완료되었습니다.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = """
                안녕하세요.
                [FliQ] 운영팀입니다.
                
                앱 업데이트가 완료되어 공지드립니다.
                
                1. 일시 : 2024년 09월 10일 14:30
                2. 업데이트 내역:
                   - 이벤트 관련 기능 추가
                   - UI 및 편의성 개선
                
                감사합니다.
            """.trimIndent(),
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

// 미리보기 함수
@Preview(showBackground = true)
@Composable
fun PreviewEventScreen() {
    EventScreen(onBack = {}, onNoticeClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewNoticeDetailScreen() {
    NoticeDetailScreen(onBack = {})
}