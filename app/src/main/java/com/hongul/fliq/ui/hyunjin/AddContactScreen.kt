package com.hongul.fliq.ui.hyunjin

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(categories: List<String>, onContactAdded: (Contact) -> Unit) {
    val selectedCategory = remember { mutableStateOf(categories.first()) }  // 기본적으로 첫 번째 카테고리 선택

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "명함 등록",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* 뒤로가기 동작 구현 */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.hy_arrow_back),
                            contentDescription = "뒤로가기",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }

    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 안내 문구
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))  // 테두리 추가
                        .padding(16.dp)  // 테두리와 텍스트 간의 여백 설정
                ) {
                    Text(
                        text = "위 명함을 명함첩에 추가합니다. \n그룹을 선택하지 않고 등록 시 '전체' 그룹에 등록됩니다.",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }


                // 그룹 선택 드롭다운
                var isDropdownExpanded by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isDropdownExpanded = !isDropdownExpanded }
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "그룹: ${selectedCategory.value}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.hy_ic_down_arrow),
                            contentDescription = "드롭다운 아이콘",
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFF125422)
                        )
                    }

                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedCategory.value = category
                                    isDropdownExpanded = false
                                },
                                text = { Text(category) }
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),  // 화면의 남은 공간 차지
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hy_card_ex),
                        contentDescription = "명함 이미지",
                        modifier = Modifier
                            .size(350.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "그룹을 추가하시면 더욱 편리하게\n 명함첩을 관리하실 수 있습니다.",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }

                Button(
                    onClick = {
                        // 명함 추가 로직
                        val newContact = Contact(
                            name = "Sample Name",
                            phoneNumber = "010-1234-5678",
                            email = "sample@email.com",
                            isFavorite = false
                        )
                        onContactAdded(newContact)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(bottom = 16.dp)  // 아래 여백 추가
                ) {
                    Text("명함 등록하기", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewAddContactScreen() {
    // 임시 데이터 준비
    val categories = listOf("전체", "개인", "업무", "취미")

    // 임시로 추가된 명함 정보를 출력하는 함수
    val onContactAdded: (Contact) -> Unit = { contact ->
        println("추가된 명함: ${contact.name}, ${contact.phoneNumber}, ${contact.email}")
    }

    AddContactScreen(
        categories = categories,
        onContactAdded = onContactAdded
    )
}
