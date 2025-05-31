package com.hongul.fliq.ui.hyunjin

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hongul.filq.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(onCategoryConfirmed: (String) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "명함",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            val bottomSheetState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberModalBottomSheetState()
            )
            val scope = rememberCoroutineScope()
            var selectedCategory by remember { mutableStateOf(0) }  // 기본 "전체" 카테고리 선택
            var categories by remember { mutableStateOf(listOf("전체", "즐겨찾기")) }  // 카테고리 수정
            var showDeleteDialog by remember { mutableStateOf<Pair<Boolean, Int>>(false to -1) }
            var showContactPopup by remember { mutableStateOf(false) }

            val favoriteContacts =
                remember { mutableStateOf(listOf<Contact>()) } // 즐겨찾기 카테고리 초기화 (빈 리스트)
            val personalContacts = remember {
                mutableStateOf(
                    listOf(
                        Contact(
                            name = "홍길동",
                            phoneNumber = "010-7169-5046",
                            email = "홍길동@example.com",
                            statusMessage = "오늘 하루도 화이팅!"
                        ),
                        Contact(
                            name = "김철수",
                            phoneNumber = "010-3250-7942",
                            email = "김철수@example.com",
                            statusMessage = "항상 좋은 하루가 될 거예요, 내일도 힘내세요!"
                        ),
                        Contact(
                            name = "이영희",
                            phoneNumber = "010-3041-5868",
                            email = "이영희@example.com",
                            statusMessage = "지금 업무 중입니다, 잠시만 기다려주세요."
                        ),
                        Contact(
                            name = "박민수",
                            phoneNumber = "010-4634-8322",
                            email = "박민수@example.com",
                            statusMessage = "연락 주세요, 급한 일이 있습니다."
                        ),
                        Contact(
                            name = "정지훈",
                            phoneNumber = "010-3141-9038",
                            email = "정지훈@example.com",
                            statusMessage = "운동하러 가는 중, 1시간 뒤에 통화 가능합니다."
                        ),
                        Contact(
                            name = "윤아",
                            phoneNumber = "010-6778-5380",
                            email = "윤아@example.com",
                            statusMessage = "쉬는 시간이에요, 잠시 후 다시 연락드릴게요."
                        ),
                        Contact(
                            name = "박지연",
                            phoneNumber = "010-5789-4317",
                            email = "박지연@example.com",
                            statusMessage = "다녀오겠습니다! 기다려주세요."
                        ),
                        Contact(
                            name = "김혜진",
                            phoneNumber = "010-3370-2763",
                            email = "김혜진@example.com",
                            statusMessage = "일 끝나고 연락드릴게요, 잠시만 기다려 주세요."
                        ),
                        Contact(
                            name = "이준호",
                            phoneNumber = "010-9433-2257",
                            email = "이준호@example.com",
                            statusMessage = "회의 중입니다, 끝나면 바로 연락드릴게요."
                        ),
                        Contact(
                            name = "장지연",
                            phoneNumber = "010-3306-5210",
                            email = "장지연@example.com",
                            statusMessage = "여유로운 오후입니다. 잠시 쉬고 있어요."
                        )
                    )
                )
            }
            var filteredContacts by remember { mutableStateOf(personalContacts.value) }

            // 정렬 기준 변수 추가
            var sortOrder by remember { mutableStateOf("이름순") }
            personalContacts.value = personalContacts.value.sortedBy { it.name }
            filteredContacts = filteredContacts.sortedBy { it.name }

            BottomSheetScaffold(
                scaffoldState = bottomSheetState,
                sheetContent = {
                    Category(
                        onAddCategory = { newCategory ->
                            if (newCategory.isNotBlank()) {
                                categories = categories + newCategory
                                scope.launch { bottomSheetState.bottomSheetState.hide() }
                            }
                        }
                    )
                },
                sheetPeekHeight = 0.dp
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // 카테고리 선택 UI
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 16.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        categories.forEachIndexed { index, category ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Button(
                                    onClick = {
                                        selectedCategory = index
                                        filteredContacts = when (index) {
                                            0 -> personalContacts.value // 전체 카테고리
                                            1 -> favoriteContacts.value // 즐겨찾기 카테고리
                                            else -> listOf() // 비어있는 카테고리
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        if (selectedCategory == index) Color(0xFF125422) else Color.Transparent
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        category,
                                        color = if (selectedCategory == index) Color.White else Color.Black,
                                        fontSize = 16.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))

                                // 기본 카테고리에는 삭제 버튼 없음
                                if (index >= 2) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "삭제",
                                        modifier = Modifier
                                            .size(16.dp)
                                            .clickable {
                                                showDeleteDialog = true to index
                                            },
                                        tint = Color.Red
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        // "+" 버튼
                        if (categories.size < 5) {
                            Button(
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                onClick = { scope.launch { bottomSheetState.bottomSheetState.expand() } },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("+", fontSize = 20.sp, color = Color.Black)
                            }
                        }
                    }

                    // 삭제 팝업
                    if (showDeleteDialog.first) {
                        DeleteCategoryDialog(
                            categoryName = categories[showDeleteDialog.second],
                            onCancel = { showDeleteDialog = false to -1 },
                            onDelete = {
                                categories = categories.toMutableList()
                                    .apply { removeAt(showDeleteDialog.second) }
                                showDeleteDialog = false to -1
                            }
                        )
                    }

                    // 검색창 추가
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var searchType by remember { mutableStateOf("이름") }
                        var isDropdownExpanded by remember { mutableStateOf(false) }
                        var searchQuery by remember { mutableStateOf("") }

                        // 드롭다운 메뉴
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .wrapContentSize(Alignment.CenterStart)
                                .clickable { isDropdownExpanded = !isDropdownExpanded }
                                .padding(horizontal = 8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = searchType,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF125422)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
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
                                DropdownMenuItem(
                                    onClick = {
                                        searchType = "이름"
                                        isDropdownExpanded = false
                                    },
                                    text = { Text("이름") }
                                )
                                DropdownMenuItem(
                                    onClick = {
                                        searchType = "회사명"
                                        isDropdownExpanded = false
                                    },
                                    text = { Text("회사명") }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // 검색 입력 필드
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = {
                                Text(
                                    text = "검색어 입력",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .background(Color.Transparent),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xFF125422),
                                unfocusedIndicatorColor = Color(0xFF125422),
                                disabledIndicatorColor = Color.Transparent
                            ),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 13.sp,
                                color = Color.Black
                            ),
                            shape = RoundedCornerShape(0.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // 검색 버튼
                        Button(
                            onClick = {
                                // TODO: 필터링
                                filteredContacts = personalContacts.value
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                            modifier = Modifier
                                .height(40.dp)
                                .padding(horizontal = 8.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("검색", color = Color.White, fontSize = 13.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 정렬 UI
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                // TODO: 정렬
                                // 정렬 방식 변경
                                if (sortOrder == "이름순") {
                                    sortOrder = "최근등록순"
                                    filteredContacts = filteredContacts // 기존 배열 순서 그대로 유지
                                } else {
                                    sortOrder = "이름순"
                                    filteredContacts =
                                        filteredContacts.sortedBy { it.name } // 이름순으로 정렬
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.hy_arrow_down_up),
                                contentDescription = "정렬 아이콘",
                                tint = Color(0xFF125422),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                sortOrder,
                                fontSize = 16.sp,
                                color = Color(0xFF125422),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // 필터링된 명함 리스트
                    if (filteredContacts.isEmpty()) {
                        // 명함이 없을 때 중앙에 이미지와 버튼 표시
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hy_daemori_image),
                                contentDescription = null,
                                modifier = Modifier.size(200.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "지인과 명함을 주고받아 편리하게 관리하세요.",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            // 명함 추가하기 버튼 클릭 시 팝업을 표시
                            Button(
                                onClick = { showContactPopup = true },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFF125422
                                    )
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text("명함 추가하기", color = Color.White)
                            }

                            // showContactPopup이 true일 때 ContactBottomSheet을 띄우기
                            if (showContactPopup) {
                                ContactBottomSheet(
                                    onDismiss = { showContactPopup = false } // 팝업 닫기
                                )
                            }
                        }
                    } else {
                        // 명함 리스트
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top
                        ) {
                            filteredContacts.forEach { contact ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    shape = RoundedCornerShape(30.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(
                                            0xFFD8F3DC
                                        )
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.hy_daemori_image),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(40.dp)
                                                .background(Color.LightGray, shape = CircleShape)
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    contact.name,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 16.sp,
                                                    color = Color(0xFF125422)
                                                )
                                                // 세로선 추가
                                                Spacer(modifier = Modifier.width(8.dp)) // 간격을 줄였어요
                                                Box(
                                                    modifier = Modifier
                                                        .height(20.dp)  // 세로선 높이 조금 줄였어요
                                                        .width(1.dp)
                                                        .background(Color(0xFF125422))
                                                )
                                                Spacer(modifier = Modifier.width(8.dp)) // 간격을 줄였어요
                                                // 상태 메시지 추가
                                                Text(
                                                    text = if (contact.statusMessage.length > 20) {
                                                        contact.statusMessage.take(20) + "..."
                                                    } else {
                                                        contact.statusMessage
                                                    },
                                                    fontSize = 12.sp,
                                                    color = Color.Gray
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Divider(
                                                color = Color(0xFF125422),
                                                thickness = 1.dp, // 가로선 추가
                                                modifier = Modifier.fillMaxWidth(0.9f)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                contact.phoneNumber,
                                                fontSize = 12.sp,
                                                color = Color(0xFF125422)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                contact.email,
                                                fontSize = 12.sp,
                                                color = Color(0xFF125422)
                                            )
                                        }

                                        // 즐겨찾기 버튼을 Row의 제일 오른쪽에 배치
                                        Icon(
                                            painter = painterResource(
                                                id = if (contact.isFavorite) R.drawable.hy_full_star else R.drawable.hy_emp_star
                                            ),
                                            contentDescription = "즐겨찾기",
                                            modifier = Modifier
                                                .size(32.dp)
                                                .clickable {
                                                    contact.isFavorite =
                                                        !contact.isFavorite // 클릭 시 즐겨찾기 상태 변경

                                                    // 즐겨찾기 해제 시 즐겨찾기 카테고리에서도 제거
                                                    if (!contact.isFavorite) {
                                                        favoriteContacts.value =
                                                            favoriteContacts.value.filter { it != contact }
                                                    } else {
                                                        favoriteContacts.value =
                                                            favoriteContacts.value + contact
                                                    }
                                                },
                                            tint = Color(0xFF125422)
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}


data class Contact(
    val name: String,
    val phoneNumber: String,
    val email: String,
    var statusMessage: String = "",  // 상태 메시지 추가
    var isFavorite: Boolean = false // 즐겨찾기 여부
)


@Composable
fun DeleteCategoryDialog(
    categoryName: String,
    onCancel: () -> Unit,
    onDelete: () -> Unit
) {
    Dialog(onDismissRequest = { onCancel() }) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\"$categoryName\" 카테고리를\n삭제하시겠습니까?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "카테고리에 있는 명함은 모두 삭제됩니다.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                    ) {
                        Text("취소", color = Color.Black)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = onDelete,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                    ) {
                        Text("삭제", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun Category(onAddCategory: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.width(40.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField 입력 부분
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("추가할 카테고리를 입력하세요") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.Black),
            isError = text.isEmpty(), // 선택 사항
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 추가 버튼
        Button(
            onClick = {
                if (text.isNotBlank()) {
                    onAddCategory(text)
                    text = "" // 입력 초기화
                }
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("추가", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun ContactBottomSheet(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "명함을 저장하고 인맥을\n관리하세요",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                CircleIconButton(
                    icon = ImageVector.vectorResource(R.drawable.hy_ic_cameraalt),
                    size = 48.dp,
                    onClick = { /* 카메라 촬영 클릭 처리 */ }
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text("명함 촬영", fontSize = 12.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Divider(
                    color = Color(0xFF125422),
                    thickness = 1.5.dp,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "명함과 대비되는 깔끔한 배경에서 촬영해 주세요.",
                    fontSize = 12.sp, color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ContactSavedPopup(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onViewCard: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "명함이 저장되었습니다.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                // 버튼들을 가로로 배치
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "확인",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            onViewCard() // 명함 보기 클릭 시 처리
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "명함 보기",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CircleIconButton(icon: ImageVector, size: Dp, onClick: () -> Unit) {
    Surface(
        shape = CircleShape,
        modifier = Modifier
            .size(size)
            .clickable(onClick = onClick)
    ) {
        Icon(
            icon, contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF7FBE85))
                .padding(10.dp),
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Category() {
    Category(onAddCategory = {})
}

@Preview(showBackground = true)
@Composable
fun ContactBottomSheetPreview() {
    ContactBottomSheet(onDismiss = {})
}

@Preview(showBackground = true)
@Composable
fun ContactSavedPopupPreview() {
    ContactSavedPopup(
        onDismiss = { /* 팝업 닫기 로직 */ },
        onViewCard = { /* 명함 보기 로직 */ }
    )
}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    ContactScreen(
        onCategoryConfirmed = { selectedCategory ->
            Log.d("ContactScreen", "선택된 카테고리: $selectedCategory")
        }
    )
}
