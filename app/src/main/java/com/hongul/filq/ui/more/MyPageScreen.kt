@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MyPageScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "마이페이지",
                        style = MaterialTheme.typography.titleLarge,
                        //textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
                .padding(paddingValues)
                .padding(24.dp) // 간격을 넓힘
        ) {
            // 프로필 정보
            ProfileInfo()
        }
    }
}

@Composable
fun ProfileInfo() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // 프로필 이미지 + 이름 및 계정
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp) // 간격을 넓힘
        ) {
            // 프로필 이미지 (원형)
            Surface(
                modifier = Modifier
                    .size(90.dp)
                    .padding(end = 20.dp),
                shape = CircleShape,
            ) {
                // 이미지 넣기 (기본 이미지를 사용)
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_camera), // 기본 이미지 리소스
                    contentDescription = "Profile Image",
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(Color.Gray) // 이미지를 회색으로 표시
                )
            }

            // 이름 및 계정
            Column {
                Text(
                    "홍길동",
                    style = TextStyle(
                        fontSize = 28.sp, // 홍길동 글자 크기 설정
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        // 구분선 색상 수정 (125422)
        Divider(color = Color(0xFF125422), thickness = 2.dp) // 구분선 색상 수정
        Spacer(modifier = Modifier.height(10.dp)) // 구분선과 텍스트 간의 간격 설정

        // 휴대전화 텍스트 및 전화번호
        Column(modifier = Modifier.padding(top = 12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center, // 아이콘과 텍스트가 중앙에 오도록 설정
                modifier = Modifier.padding(bottom = 16.dp) // 간격 추가
            ) {
                // 큰 전화 아이콘
                Icon(
                    Icons.Filled.Phone, contentDescription = "전화번호",
                    modifier = Modifier.size(30.dp), // 아이콘 크기 조정
                    tint = Color(0x33000000) // 아이콘 색상 변경 (20% 투명도 적용)
                )
                Spacer(modifier = Modifier.width(24.dp)) // 아이콘과 텍스트 사이 간격 추가

                // "휴대전화" 텍스트
                Column {
                    Text(
                        "휴대전화",
                        style = TextStyle(
                            fontSize = 18.sp, // 글자 크기 직접 설정 (예: 20.sp)
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF125422)
                        )
                    ) // 텍스트 색상 추가
                    Spacer(modifier = Modifier.height(10.dp)) // 간격 추가
                    Text(
                        "010-1234-5678",
                        style = TextStyle(
                            fontSize = 18.sp, // 글자 크기 직접 설정 (예: 20.sp)
                            fontWeight = FontWeight.Bold
                        )
                    ) // 전화번호
                }
            }
            // 휴대전화 아래에 10% 투명한 선
            Divider(color = Color(0x2A585656), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp)) // 구분선과 다음 텍스트 간의 간격 설정
        }

        // 이메일 텍스트 및 이메일 주소
        Column(modifier = Modifier.padding(top = 12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center, // 아이콘과 텍스트가 중앙에 오도록 설정
                modifier = Modifier.padding(bottom = 16.dp) // 간격 추가
            ) {
                // 큰 이메일 아이콘
                Icon(
                    Icons.Filled.Email, contentDescription = "이메일",
                    modifier = Modifier.size(30.dp), // 아이콘 크기 조정
                    tint = Color(0x33000000) // 아이콘 색상 변경 (20% 투명도 적용)
                )
                Spacer(modifier = Modifier.width(24.dp)) // 아이콘과 텍스트 사이 간격 추가

                // "이메일" 텍스트
                Column {
                    Text(
                        "이메일",
                        style = TextStyle(
                            fontSize = 18.sp, // 글자 크기 직접 설정 (예: 20.sp)
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF125422)
                        )
                    ) // 텍스트 색상 추가
                    Spacer(modifier = Modifier.height(10.dp)) // 간격 추가
                    Text(
                        "xxx@stu.kmu.ac.kr",
                        style = TextStyle(
                            fontSize = 18.sp, // 글자 크기 직접 설정 (예: 20.sp)
                            fontWeight = FontWeight.Bold
                        )
                    ) // 이메일 주소
                }
            }
            // 이메일 아래에 10% 투명한 선
            Divider(color = Color(0x2A585656), thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    MyPageScreen(onBack = {})
}
