package com.hongul.filq.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import com.hongul.filq.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginScreen(
    onBackPress: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onPasswordResetClick: () -> Unit = {}
) {
    val customGreen = Color(0xFF125422)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back_ios),
                            contentDescription = "뒤로 가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // 환영합니다
            Text(
                text = "환영합니다",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = customGreen,
                modifier = Modifier.padding(bottom = 25.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 이메일
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "이메일",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    color = customGreen,
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                )
                BasicTextField(
                    value = email,
                    onValueChange = { email = it },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    ),
                    cursorBrush = SolidColor(customGreen),
                    decorationBox = { innerTextField ->
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, top = 4.dp, bottom = 4.dp)
                            ) {
                                if (email.isEmpty()) {
                                    Text(
                                        text = "이메일 입력",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Gray.copy(alpha = 0.6f)
                                    )
                                }
                                innerTextField()
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .padding(horizontal = 16.dp)
                                    .background(customGreen)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 비밀번호
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "비밀번호",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    color = customGreen,
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                )
                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    ),
                    cursorBrush = SolidColor(customGreen),
                    decorationBox = { innerTextField ->
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, top = 4.dp, bottom = 4.dp)
                            ) {
                                if (password.isEmpty()) {
                                    Text(
                                        text = "비밀번호 입력",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Gray.copy(alpha = 0.6f)
                                    )
                                }
                                innerTextField()
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .padding(horizontal = 16.dp)
                                    .background(customGreen)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 비밀번호를 잊으셨나요
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "비밀번호를 잊으셨나요?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = onPasswordResetClick, contentPadding = PaddingValues(0.dp)) {
                    Text(
                        text = "비밀번호 재설정",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        color = customGreen,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 로그인 버튼
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = customGreen)
            ) {
                Text(
                    text = "로그인",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
