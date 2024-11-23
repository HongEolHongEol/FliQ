package com.hongul.filq.ui.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.hongul.filq.R
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.BusinessCard
import com.hongul.filq.model.SNS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharingFriend(
    businessCard: BusinessCard,
    onClickProfileImage: () -> Unit,
    onBackPress: () -> Unit = {} // 기본값 추가
) {
    val titleColor = Color(0xFF125422)
    val contentColor = Color.Black
    val deleteTextColor = Color(0xFFB00020)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back_ios), // 커스텀 벡터 리소스 사용
                            contentDescription = "뒤로 가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 명함 사진
                    Image(
                        painter = painterResource(id = R.drawable.ic_hongchuping),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .padding(bottom = 16.dp)
                    )

                    // 흰 박스 복원
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)) // 그림자 추가
                            .background(color = Color.White, shape = RoundedCornerShape(16.dp)) // 흰 배경 추가
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "홍츄핑",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = businessCard.introduction ?: "",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(bottom = 16.dp)
                                    .align(Alignment.CenterHorizontally),
                                textAlign = TextAlign.Center
                            )

                            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_memo),
                                        contentDescription = "Memo Icon",
                                        modifier = Modifier.size(45.dp)
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_category),
                                        contentDescription = "Category Icon",
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                            }

                            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

                            // 연락처 정보
                            Text(
                                text = "휴대전화",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = businessCard.phoneNumber ?: "",
                                fontSize = 14.sp,
                                color = contentColor,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            Text(
                                text = "이메일",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = businessCard.email ?: "",
                                fontSize = 14.sp,
                                color = contentColor,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // SNS
                            Text(
                                text = "SNS",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                // Instagram
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_insta),
                                        contentDescription = "Instagram Icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "@hongchuchuchu",
                                        fontSize = 14.sp,
                                        color = contentColor
                                    )
                                }
                                // Kakao
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_kakao),
                                        contentDescription = "Kakao Icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "hongchuchu",
                                        fontSize = 14.sp,
                                        color = contentColor

                                    )
                                }
                            }
                            // 태그
                            Text(
                                text = "태그",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 4.dp).padding(top = 16.dp)
                            )
                            Text(
                                text = "React Node.js Typescript",
                                fontSize = 14.sp,
                                color = contentColor,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // QR 코드
                            Text(
                                text = "QR 코드",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_qr),
                                    contentDescription = "QR Code",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(bottom = 16.dp)
                                )
                            }
                        }
                    }
                }

                // 명함 삭제하기
                Text(
                    text = "명함 삭제하기",
                    fontSize = 14.sp,
                    color = deleteTextColor,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SharingFriendPreview() {
    val sampleBusinessCard = BusinessCard(
        name = "홍츄핑",
        title = "계명대학교 학생",
        phoneNumber = "010-1234-5678",
        email = "gildong@gmail.com",
        address = "대구광역시 달서구 신당동",
        organization = "계명대학교",
        department = "컴퓨터공학부",
        position = "개발자",
        sns = listOf(
            SNS.Instagram("https://www.instagram.com/hongchuchuchu"),
            SNS.Facebook("https://kakao.com/hongpingchu")
        ),
        imagePath = "image.png",
        avatar = Avatar(),
        introduction = "안녕하세요.\n계명대학교 재학중인 홍츄핑입니다!"
    )
    SharingFriend(
        businessCard = sampleBusinessCard,
        onClickProfileImage = {},
        onBackPress = { }
    )
}
