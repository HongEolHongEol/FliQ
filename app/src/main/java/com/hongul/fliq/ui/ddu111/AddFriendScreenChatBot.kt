package com.hongul.fliq.ui.ddu111

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendScreenChatBot(
    name: String,
    tags: String,
    onBackPress: () -> Unit = {}
) {
    val titleColor = Color(0xFF125422)
    val contentColor = Color.Black

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            painter = painterResource(id = R.drawable.su_arrow_back_ios),
                            contentDescription = "뒤로 가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.su_ic_hongchuping),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .padding(bottom = 16.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = contentColor,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.su_ic_chatbot),
                                    contentDescription = "챗봇 아이콘",
                                    tint = Color(0xFF125422),
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clickable { }
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "챗봇",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF125422)
                                )
                            }

                            Divider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            Text(
                                text = "SNS",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.su_ic_insta),
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

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.su_ic_kakao),
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

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "태그",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = tags,
                                fontSize = 14.sp,
                                color = contentColor,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            Text(
                                text = "QR 코드",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = titleColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.su_ic_qr),
                                contentDescription = "QR Code",
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddFriendScreenChatBotPreview() {
    AddFriendScreenChatBot(
        name = "홍츄핑",
        tags = "#자바 #프론트 #AI"
    )
}
