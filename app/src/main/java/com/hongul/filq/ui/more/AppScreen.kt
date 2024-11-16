@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.*

@Composable
fun AppScreen() {
    val currentScreen = remember { mutableStateOf("더보기") }

    when (currentScreen.value) {
        "더보기" -> MoreScreen(
            onNavigateToMyPage = { currentScreen.value = "마이페이지" },
            onNavigateToEvent = { currentScreen.value = "이벤트" },
            onNavigateToInquiry = { currentScreen.value = "1:1 문의" },
            onNavigateToSettings = { currentScreen.value = "설정" }
        )
        "마이페이지" -> MyPageScreen(onBack = { currentScreen.value = "더보기" })
        "이벤트" -> EventScreen(
            onBack = { currentScreen.value = "더보기" },
            onNoticeClick = { currentScreen.value = "이벤트 상세보기" }
        )
        "1:1 문의" -> OneToOneInquiryScreen(
            onBack = { currentScreen.value = "더보기" },
            onSubmit = { /* 문의하기 버튼 클릭 시 수행할 동작 추가 */ }
        )
        "설정" -> SettingsScreen(
            onBack = { currentScreen.value = "더보기" }
        )
    }
}
