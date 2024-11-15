@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "설정",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
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
                .padding(horizontal = 16.dp)
        ) {
            SettingsItem(text = "계정 설정", showArrow = true, onClick = { /* 계정 설정으로 이동 로직 */ })
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)

            SettingsItem(text = "이용 약관", showArrow = true, onClick = { /* 이용 약관 화면으로 이동 로직 */ })
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)

            SettingsItem(text = "개인정보처리방침", showArrow = true, onClick = { /* 개인정보처리방침 화면으로 이동 로직 */ })
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
        }
    }
}

@Composable
fun SettingsItem(text: String, onClick: () -> Unit, showArrow: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.weight(1f)
        )
        if (showArrow) {
            Text(
                text = ">",
                color = Color(0xFF585656),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
