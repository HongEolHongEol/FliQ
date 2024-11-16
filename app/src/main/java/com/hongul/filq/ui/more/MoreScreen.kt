@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MoreScreen(
    onNavigateToMyPage: () -> Unit,
    onNavigateToEvent: () -> Unit,
    onNavigateToInquiry: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "더보기",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* 뒤로 가기 로직 */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "뒤로 가기")
                    }
                },
                actions = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
        ) {
            // Each menu item in the More Screen
            MoreMenuItem("마이페이지", Icons.Filled.Person, onClick = onNavigateToMyPage)
            Divider(color = Color(0x1A585656), thickness = 1.dp, modifier = Modifier.fillMaxWidth())

            MoreMenuItem("이벤트/공지사항", Icons.Filled.Notifications, onClick = onNavigateToEvent)
            Divider(color = Color(0x1A585656), thickness = 1.dp, modifier = Modifier.fillMaxWidth())

            MoreMenuItem("1:1 문의", Icons.Filled.Email, onClick = onNavigateToInquiry)
            Divider(color = Color(0x1A585656), thickness = 1.dp, modifier = Modifier.fillMaxWidth())

            MoreMenuItem("설정", Icons.Filled.Settings, onClick = onNavigateToSettings)
            Divider(color = Color(0x1A585656), thickness = 1.dp, modifier = Modifier.fillMaxWidth())
        }
    }
}

// Composable function to render individual menu items
@Composable
fun MoreMenuItem(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0xFF585656),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Color.Black
        )
    }
}
