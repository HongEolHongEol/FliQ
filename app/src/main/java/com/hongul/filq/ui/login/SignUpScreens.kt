package com.hongul.filq.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.navigation.NavHostController
import com.hongul.filq.R
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape


// 회원가입 단계 설정
sealed class SignUpScreens(val route:String){
    object PhoneInput : SignUpScreens("phone_input") // 휴대폰 입력 화면
    object EmailInput : SignUpScreens("email_input") // 이메일 입력 화면
    object PasswordInput : SignUpScreens("password_input") // 비밀번호 입력 화면
    object NameInput : SignUpScreens("name_input") // 이름 입력 화면
    object Complete : SignUpScreens("complete") // 회원가입 완료 화면
}

// 휴대폰 입력 화면
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneInputScreen(onBackPress: () -> Unit = {}, onNext: () -> Unit = {}) {
    var phoneNumber by remember { mutableStateOf("") }
    val isPhoneValid = phoneNumber.length == 11 && phoneNumber.all { it.isDigit() }

    val customGreen = Color(0xFF125422) // #125422 색상 정의

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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "휴대폰 번호를 입력해 주세요",
                style = MaterialTheme.typography.titleMedium.copy(color = customGreen),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            BasicTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it.take(11) },
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            if (phoneNumber.isEmpty()) {
                                Text(
                                    text = "휴대폰 번호 입력",
                                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                )
                            }
                            innerTextField()
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(customGreen)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onNext,
                enabled = isPhoneValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPhoneValid) customGreen else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(48.dp) ,
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "다음",
                    color = if (isPhoneValid) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}
@Preview(showBackground = true, name = "Phone Input Screen Preview")
@Composable
fun PreviewPhoneInputScreen() {
    PhoneInputScreen()
}



// 이메일 입력 화면
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EmailInputScreen(onBackPress: () -> Unit = {}, onNext: () -> Unit = {}) {
    var emailAdress by remember { mutableStateOf("") }
    val isEmailValid = emailAdress.length == 11 && emailAdress.all { it.isDigit() }

    val customGreen = Color(0xFF125422)

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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "FliQ에서 사용할 이메일을 입력해 주세요",
                style = MaterialTheme.typography.titleMedium.copy(color = customGreen),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            BasicTextField(
                value = emailAdress,
                onValueChange = { emailAdress = it.take(11) },
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            if (emailAdress.isEmpty()) {
                                Text(
                                    text = "이메일 주소 입력",
                                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                )
                            }
                            innerTextField()
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(customGreen)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onNext,
                enabled = isEmailValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isEmailValid) customGreen else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(48.dp) ,
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "다음",
                    color = if (isEmailValid) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewEmailInputScreen() {
    EmailInputScreen()
}


// 이름 입력 화면
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NameInputScreen(onBackPress: () -> Unit = {}, onNext: () -> Unit = {}) {
    var name by remember { mutableStateOf("") }
    val isNameValid = name.length == 11 && name.all { it.isDigit() }

    val customGreen = Color(0xFF125422)

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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "이름을 입력해 주세요",
                style = MaterialTheme.typography.titleMedium.copy(color = customGreen),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            BasicTextField(
                value = name,
                onValueChange = { name = it.take(11) },
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            if (name.isEmpty()) {
                                Text(
                                    text = "이름 입력",
                                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                )
                            }
                            innerTextField()
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(customGreen)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onNext,
                enabled = isNameValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNameValid) customGreen else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "다음",
                    color = if (isNameValid) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewNameInputScreen() {
    NameInputScreen()
}


// 비밀번호 입력 화면
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PasswordInputScreen(onBackPress: () -> Unit = {}, onNext: () -> Unit = {}) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val customGreen = Color(0xFF125422)

    // 비밀번호 유효성 검사
    val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,16}$")
    val isPasswordValid = passwordPattern.matches(password)
    val isPasswordMatch = password == confirmPassword

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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "비밀번호를\n입력해 주세요",
                style = MaterialTheme.typography.titleMedium.copy(color = customGreen),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 비밀번호 입력
            Column(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = password,
                    onValueChange = { password = it.take(16) },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    decorationBox = { innerTextField ->
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                            ) {
                                if (password.isEmpty()) {
                                    Text(
                                        text = "비밀번호 입력",
                                        style = TextStyle(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                    )
                                }
                                innerTextField()
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .background(customGreen)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // 비밀번호 조건
                if (!isPasswordValid && password.isNotEmpty()) {
                    Text(
                        text = "비밀번호는 8~16자의 영문, 숫자, 특수문자를 포함해야 합니다.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 비밀번호 확인 입력 필드
            Column(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it.take(16) },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    decorationBox = { innerTextField ->
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                            ) {
                                if (confirmPassword.isEmpty()) {
                                    Text(
                                        text = "비밀번호 재입력",
                                        style = TextStyle(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                    )
                                }
                                innerTextField()
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .background(customGreen)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // 비밀번호 일치 여부 표시
                if (!isPasswordMatch && confirmPassword.isNotEmpty()) {
                    Text(
                        text = "비밀번호가 일치하지 않습니다.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onNext,
                enabled = isPasswordValid && isPasswordMatch, // 비번 일치 유효성
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPasswordValid && isPasswordMatch) customGreen else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "다음",
                    color = if (isPasswordValid && isPasswordMatch) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordInputScreen() {
    PasswordInputScreen()
}


//회원가입 완료
@Composable
fun SignUpCompleteScreen(onNext: () -> Unit = {}) {
    val customGreen = Color(0xFF125422) // 커스텀 초록색

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "환영합니다 ~",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            ),
            modifier = Modifier.padding(start = 20.dp) // 오른쪽으로 이동
        )

        Spacer(modifier = Modifier.height(8.dp))

        // "FliQ 회원가입이" 텍스트를 약간 오른쪽으로 이동
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp) // 오른쪽으로 이동
        ) {
            Text(
                text = "FliQ",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = customGreen,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "회원가입이",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "완료되었습니다 !",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(start = 20.dp) // 오른쪽으로 이동
        )

        Spacer(modifier = Modifier.height(10.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_login), // "ic_login" 아이콘 사용
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(350.dp) // 아이콘 크기 확대
                .align(Alignment.CenterHorizontally) // 중앙 정렬
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = customGreen)
        ) {
            Text(
                text = "로그인 화면으로 돌아가기",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewSignUpCompleteScreen() {
    SignUpCompleteScreen()
}
