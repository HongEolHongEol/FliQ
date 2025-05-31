package com.hongul.fliq.ui.ddu111

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.R

// Contact 데이터 클래스 정의
data class Contact(
    val name: String,
    val phone: String,
    val email: String,
    val statusMessage: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagScreen(onAddFriendScreen: (String, String) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "태그",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            var searchQuery by remember { mutableStateOf("") }
            val recommendedTags = listOf("#프론트", "#C++", "#파이썬", "#코틀린", "#자바")
            val allContacts = listOf(
                Contact("홍츄핑", "", "", "#자바 #홍홍"),
                Contact("홍박사", "", "", "#자바 #홍"),
                Contact("홍추핑구", "", "", "#자바 #홍"),
                Contact("홍길동", "", "", "#자바 #C"),
                Contact("김갑순", "", "", "#자바 #파이썬"),
                Contact("백수연", "", "", "#자바 #C++"),
                Contact("윤주원", "", "", "#자바 #프론트"),
                Contact("홍구", "", "", "#자바 #C")
            )
            var filteredContacts by remember { mutableStateOf(allContacts) }

            Column(modifier = Modifier.fillMaxSize()) {
                // 검색창
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            filteredContacts = allContacts.filter { contact ->
                                contact.statusMessage.contains(it, ignoreCase = true)
                            }
                        },
                        placeholder = {
                            Text("태그 검색", fontSize = 13.sp, color = Color.Gray)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFF125422),
                            unfocusedIndicatorColor = Color(0xFF125422),
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        textStyle = LocalTextStyle.current.copy(fontSize = 13.sp)
                    )


                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            filteredContacts = allContacts.filter { contact ->
                                contact.statusMessage.contains(searchQuery, ignoreCase = true)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                        modifier = Modifier.height(40.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("검색", color = Color.White, fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 추천 태그
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    recommendedTags.forEach { tag ->
                        Box(
                            modifier = Modifier
                                .background(Color.Transparent, shape = RoundedCornerShape(16.dp))
                                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(16.dp))
                                .clickable {
                                    searchQuery = tag
                                    filteredContacts = allContacts.filter { contact ->
                                        contact.statusMessage.contains(tag, ignoreCase = true)
                                    }
                                }
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(tag, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 명함 리스트
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    filteredContacts.forEach { contact ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    onAddFriendScreen(contact.name, contact.statusMessage)
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFD8F3DC))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .background(Color(0xFF7FBE85), shape = CircleShape)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.su_stickers1),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(55.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(
                                        text = contact.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = Color(0xFF125422)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Divider(
                                        color = Color(0xFF125422),
                                        thickness = 1.dp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = contact.statusMessage,
                                        fontSize = 14.sp,
                                        color = Color.Gray
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

@Preview(showBackground = true)
@Composable
fun TagScreenPreview() {
    TagScreen { name, tags ->
        Log.d("TagScreenPreview", "Selected: $name, $tags")
    }
}