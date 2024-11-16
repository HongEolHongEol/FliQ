@file:OptIn(ExperimentalMaterial3Api::class)

package com.hongul.filq.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    var currentScreen by remember { mutableStateOf("설정") }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showWithdrawalDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = when (currentScreen) {
                            "비밀번호 변경" -> "비밀번호 변경"
                            "계정 인증 방법 선택" -> "계정 인증 방법 선택"
                            "비밀번호 재설정" -> "비밀번호 재설정"
                            "회원탈퇴" -> "회원탈퇴"
                            else -> "계정 설정"
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentScreen != "설정") {
                            currentScreen = "설정"
                        } else {
                            onBack()
                        }
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "뒤로 가기")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (currentScreen) {
            "비밀번호 변경" -> PasswordChangeContent(
                paddingValues,
                onNavigateToVerification = { currentScreen = "계정 인증 방법 선택" }
            )
            "계정 인증 방법 선택" -> AccountVerificationContent(
                paddingValues,
                onNavigateToResetPassword = { currentScreen = "비밀번호 재설정" }
            )
            "비밀번호 재설정" -> PasswordResetContent(
                paddingValues = paddingValues,
                onPasswordResetComplete = { currentScreen = "설정" } // 재설정 완료 후 설정 화면으로 이동
            )
            "회원탈퇴" -> WithdrawalContent(
                paddingValues,
                onConfirm = { showWithdrawalDialog = true }
            )
            else -> SettingsContent(
                paddingValues = paddingValues,
                onPasswordChangeClick = { currentScreen = "비밀번호 변경" },
                onLogoutClick = { showLogoutDialog = true },
                onWithdrawalClick = { currentScreen = "회원탈퇴" }
            )
        }

        if (showLogoutDialog) {
            LogoutDialog(
                onDismiss = { showLogoutDialog = false },
                onConfirm = {
                    showLogoutDialog = false
                    // 로그아웃 처리 로직 추가
                }
            )
        }

        if (showWithdrawalDialog) {
            WithdrawalConfirmationDialog(
                onDismiss = { showWithdrawalDialog = false },
                onConfirm = {
                    showWithdrawalDialog = false
                    // 회원탈퇴 처리 로직 추가
                }
            )
        }
    }
}

@Composable
fun SettingsContent(
    paddingValues: PaddingValues,
    onPasswordChangeClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onWithdrawalClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        SettingsItem(
            text = "비밀번호 변경",
            onClick = onPasswordChangeClick,
            showArrow = true
        )
        Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)

        SettingsItem(
            text = "로그아웃",
            onClick = onLogoutClick,
            showArrow = true
        )
        Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)

        Text(
            text = "회원탈퇴",
            color = Color(0xFF125422),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clickable { onWithdrawalClick() }
        )
    }
}

@Composable
fun PasswordChangeContent(paddingValues: PaddingValues, onNavigateToVerification: () -> Unit) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "FliQ 계정을 입력하세요",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF125422)
            ),
            modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("이메일 주소 입력") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (email.contains("@")) {
                    onNavigateToVerification()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (email.contains("@")) Color(0xFF125422) else Color.Gray
            ),
            enabled = email.contains("@")
        ) {
            Text("다음", color = Color.White)
        }
    }
}

@Composable
fun AccountVerificationContent(
    paddingValues: PaddingValues,
    onNavigateToResetPassword: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "계정 인증 방법을 선택하세요",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF125422)
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        VerificationItem(
            title = "문자 메시지로 인증하기",
            subtitle = "+82 10 0000 0000",
            onClick = { onNavigateToResetPassword() }
        )
        Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)

        VerificationItem(
            title = "이메일로 인증하기",
            subtitle = "xxx.str.kmu.ac.kr",
            onClick = { onNavigateToResetPassword() }
        )
        Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
    }
}

@Composable
fun PasswordResetContent(
    paddingValues: PaddingValues,
    onPasswordResetComplete: () -> Unit
) {
    var code by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }
    var timeLeft by remember { mutableStateOf(180) } // 3분 타이머
    var showSuccessDialog by remember { mutableStateOf(false) } // 성공 다이얼로그 표시 여부

    // 타이머 시작
    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            kotlinx.coroutines.delay(1000)
            timeLeft -= 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "인증번호를 입력하고,\n새로운 비밀번호를 설정하세요",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF125422)
            ),
            modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
        )

        // 인증번호 입력 필드
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = code,
                onValueChange = {
                    code = it
                    errorMessage = "" // 인증번호 오류 초기화
                },
                label = { Text("인증번호") },
                modifier = Modifier.weight(1f),
                isError = errorMessage.isNotEmpty(),
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (timeLeft > 0) {
                            Text(
                                text = "${timeLeft / 60}:${String.format("%02d", timeLeft % 60)}",
                                color = Color.Red,
                                fontSize = 12.sp
                            )
                        }
                        TextButton(onClick = {
                            // 재전송 로직
                            timeLeft = 180 // 타이머 리셋
                            code = ""
                            errorMessage = ""
                        }) {
                            Text("재전송", color = Color(0xFF125422))
                        }
                    }
                }
            )
        }
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 입력 필드
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordErrorMessage = "" // 비밀번호 오류 초기화
            },
            label = { Text("비밀번호 입력") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 재입력 필드
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                passwordErrorMessage = "" // 비밀번호 오류 초기화
            },
            label = { Text("비밀번호 재입력") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
        )
        if (passwordErrorMessage.isNotEmpty()) {
            Text(
                text = passwordErrorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 버튼
        Button(
            onClick = {
                when {
                    code != "111" -> { // 인증번호 틀림
                        errorMessage = "인증번호를 다시 확인해 주세요"
                    }
                    password != confirmPassword -> { // 비밀번호 불일치
                        passwordErrorMessage = "비밀번호가 일치하지 않습니다."
                    }
                    else -> {
                        errorMessage = ""
                        passwordErrorMessage = ""
                        showSuccessDialog = true // 성공 다이얼로그 표시
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = if (code.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) Color(0xFF125422) else Color.Gray),
            enabled = code.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
        ) {
            Text("비밀번호 재설정", color = Color.White)
        }
    }

    // 성공 다이얼로그
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = {
                Text(
                    text = "비밀번호가 변경되었습니다!",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        onPasswordResetComplete() // 성공 후 동작 (예: 메인 화면으로 이동)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("확인", color = Color.White) //todo
                }
            },
            shape = RoundedCornerShape(12.dp),
            containerColor = Color.White
        )
    }
}


@Composable
fun VerificationItem(title: String, subtitle: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun LogoutDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "로그아웃 하시겠습니까?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text("닫기", color = Color.White)
                }
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text("확인", color = Color.White)
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        containerColor = Color.White
    )
}

@Composable
fun WithdrawalConfirmationDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "탈퇴로 처리되었습니다.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("확인", color = Color.White)
            }
        },
        shape = RoundedCornerShape(12.dp),
        containerColor = Color.White
    )
}

@Composable
fun WithdrawalContent(
    paddingValues: PaddingValues,
    onConfirm: () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "회원 탈퇴",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 16.dp)
        )

        Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            Column {
                Text("1. 탈퇴하시면 등록한 연락처는 모두 삭제되어 복구할 수 없습니다.")
                Text("2. 휴대폰 번호 변경은 [마이페이지]>[계정 설정]에서 가능합니다.")
                Text("3. 이메일 변경은 [마이페이지]>[계정 설정]에서 가능합니다.")
                Text("4. 불편한 점이 있다면 [1:1 문의]로 내용을 남겨주세요.")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isChecked = !isChecked },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "전부 삭제하고 탈퇴하겠습니다")
        }

        Button(
            onClick = onConfirm,
            enabled = isChecked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isChecked) Color(0xFF125422) else Color.Gray
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("탈퇴하기", color = Color.White)
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
