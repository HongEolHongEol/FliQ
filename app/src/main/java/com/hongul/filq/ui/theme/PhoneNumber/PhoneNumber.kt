package com.hongul.filq.ui.theme.PhoneNumber
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CameraAlt
//import androidx.compose.material.icons.filled.PhotoLibrary
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material.icons.filled.Phone
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import androidx.compose.material3.rememberBottomSheetScaffoldState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment.Companion.CenterVertically
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.vectorResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.Dp
//import com.hongul.filq.R
//import kotlinx.coroutines.launch
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PhoneNumberScreen(modifier: Modifier = Modifier) {
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        "명함", fontSize = 20.sp, fontWeight = FontWeight.Bold,
//                        textDecoration = TextDecoration.Underline
//                    )
//                },
//                navigationIcon = {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
//                        contentDescription = null
//                    )
//                }
//            )
//        }
//    ) { innerPadding ->
//        Surface(modifier = Modifier.padding(innerPadding)) { // 기본 배경
//            val bottomSheetState = rememberBottomSheetScaffoldState(
//                bottomSheetState = rememberModalBottomSheetState()
//            )
//            val scope = rememberCoroutineScope()
//            var selectedCategory by remember { mutableStateOf(0) } // 카테고리 선택, 기본값: '개인' 버튼
//            var categories by remember { mutableStateOf(listOf("개인", "업무")) } // 초기 카테고리 리스트
//            var showBottomSheet by remember { mutableStateOf(false) } // BottomSheet 표시 여부 상태 변수 추가
//            var showDialog by remember { mutableStateOf(false) } // Dialog 표시 여부 상태 변수 추가
//            BottomSheetScaffold(
//                scaffoldState = bottomSheetState,
//                sheetContent = {
//                    Category(
//                        onAddCategory = { newCategory ->
//                            if (newCategory.isNotBlank()) {
//                                categories = categories + newCategory // 새로운 카테고리 추가
//                                scope.launch { bottomSheetState.bottomSheetState.hide() }
//                            }
//                        }
//                    )
//                },
//                sheetPeekHeight = 0.dp // 처음에는 시트를 숨기기 위해 높이를 0으로 설정
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(vertical = 20.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Row {
//                        categories.forEachIndexed { index, category ->
//                            Button(
//                                onClick = { selectedCategory = index },
//                                colors = ButtonDefaults.buttonColors(
//                                    if (selectedCategory == index) Color(0xFF125422) else Color.Transparent
//                                )
//                            ) {
//                                Text(
//                                    category,
//                                    color = if (selectedCategory == index) Color.White else Color.Black
//                                )
//                            }
//                            Spacer(modifier = Modifier.width(10.dp))
//                        }
//
//                        Button(
//                            colors = ButtonDefaults.buttonColors(Color.Transparent), // 투명색
//                            onClick = { scope.launch { bottomSheetState.bottomSheetState.expand() } }
//                        ) {
//                            Text("+", fontSize = 25.sp, color = Color.Black)
//                        }
//                    }
//                }
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.daemori_image),
//                        contentDescription = null,
//                        modifier = Modifier.size(200.dp)
//                    )
//
//                    Text(
//                        "지인의 명함이나 연락처를 추가하여\n편리하게 관리하세요.",
//                        fontSize = 12.sp,
//                        color = Color.Gray,
//                        textAlign = TextAlign.Center, // 중앙 정렬
//                        modifier = Modifier.padding(16.dp)
//                    )
//
//                    Button(
//                        onClick = { showDialog = true },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = ButtonDefaults.buttonColors(Color(0xFF125422))
//                    ) {
//                        Text("연락처 추가하기")
//                    }
//
//
//
//                    if (showBottomSheet) { // showBottomSheet 상태가 true일 때 BottomSheet 표시
//                        BottomSheetScaffold(
//                            scaffoldState = bottomSheetState,
//                            sheetContent = {
//                                ContactBottomSheet(
//                                    onDismiss = { showBottomSheet = false } // 닫기 콜백 추가
//                                )
//                            },
//                            sheetPeekHeight = 0.dp // 처음에는 시트를 숨기기 위해 높이를 0으로 설정
//                        ) {
//                        }
//                    }
//
//                    if (showDialog) { // showDialog 상태가 true일 때 Dialog 표시
//                        ContactBottomSheet(onDismiss = { showDialog = false }) // onDismiss 콜백 추가
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Category(onAddCategory: (String) -> Unit) {
//    var text by remember { mutableStateOf("") }
//
//    Spacer(modifier = Modifier.width(40.dp))
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        TextField(
//            value = text,
//            onValueChange = { text = it },
//            placeholder = { Text("추가할 카테고리를 입력하세요") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)), // 밝은 회색 배경
//            shape = RoundedCornerShape(8.dp), // 둥근 모서리
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                onAddCategory(text)
//                text = "" // 입력 초기화
//            },
//            modifier = Modifier.align(Alignment.End),
//            colors = ButtonDefaults.buttonColors(Color.Transparent)
//        ) {
//            Text("추가", color = Color(0xFF125422), fontWeight = FontWeight.Bold)
//        }
//    }
//}
//
//@Composable
//fun ContactBottomSheet(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
//    Dialog(onDismissRequest = { onDismiss()}) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ){
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 30.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Text("연락처를 저장하고 인맥을\n관리하세요", style = MaterialTheme.typography.bodyLarge,
//                    color = Color.Gray,
//                    textAlign = TextAlign.Center, // 중앙 정렬
//                    fontWeight = FontWeight.SemiBold
//                )
//
//                Spacer(modifier = Modifier.height(30.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly // 아이템 사이와 양쪽 끝에 동일한 간격을 두고 아이템을 배치
//                ) {
//                    CircleIconButton(
//                        icon = Icons.Default.CameraAlt,
//                        size = 48.dp,
//                        onClick = { /* 카메라 촬영 클릭 처리 */ }
//                    )
//                    CircleIconButton (
//                        icon = Icons.Default.PhotoLibrary,
//                        size = 48.dp,
//                        onClick = {}
//                    )
//                    CircleIconButton(
//                        icon = Icons.Default.Edit,
//                        size = 48.dp,
//                        onClick = {}
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Divider( // 선 추가
//                    color = Color(0xFF125422),
//                    thickness = 1.5.dp,
//                    modifier = Modifier.fillMaxWidth(0.8f) // 선의 길이를 부모 너비의 80%로 설정
//                )
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Button(
//                        onClick = { /* 버튼 클릭 처리 */ },
//                        colors = ButtonDefaults.buttonColors(Color.Transparent),
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Icon(Icons.Default.Phone, contentDescription = "전화 아이콘",
//                            modifier = Modifier.padding(3.dp), tint = Color.Black)
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(
//                            "내 연락처에서 가져오기\n휴대폰에서 저장된 연락처를 가져오세요.",
//                            fontSize = 10.sp, color = Color.Black
//                        )
//                    }
//
//                }
//            }
//        }
//
//    }
//}
//
//
//@Composable
//fun CircleIconButton(icon: ImageVector, size: Dp, onClick: () -> Unit) {
//    Surface(
//        shape = CircleShape,
//        modifier = Modifier.size(size).clickable(onClick = onClick)
//    ) {
//        Icon(icon, contentDescription = null,
//            modifier = Modifier.fillMaxSize().background(Color(0xFF7FBE85)).padding(10.dp),
//            tint = Color.White)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun Category() {
//    Category(onAddCategory = {})
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ContactBottomSheetPreview() {
//    ContactBottomSheet(onDismiss = {})
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PhoneNumberPreview() {
//    PhoneNumberScreen(Modifier.fillMaxSize())
//}
//
//
