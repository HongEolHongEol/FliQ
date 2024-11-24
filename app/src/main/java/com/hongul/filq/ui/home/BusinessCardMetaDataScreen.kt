package com.hongul.filq.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.hongul.filq.ui.theme.FilQTheme
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardMetaDataScreen() {
    val titleColor = Color(0xFF125422)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("명함 편집", fontSize = 20.sp) },
                navigationIcon = { IconButton(onClick = {}) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, null)
                } }
            )
        }
    ) { innerPadding ->
        // 스크롤
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "이름 또는 닉네임",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
                modifier = Modifier.padding()
                    .padding(start = 8.dp, bottom = 4.dp) // 오른쪽으로 이동
            )
            BusinessCardItem(icon = R.drawable.ic_user, text = "홍알홍알")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "명함 제목",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
                modifier = Modifier.padding()
                    .padding(start = 8.dp, bottom = 4.dp) // 오른쪽으로 이동
            )
            BusinessCardItem(icon = R.drawable.ic_group, text = "캡스톤(1)")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "휴대폰 번호",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
                modifier = Modifier.padding()
                    .padding(start = 8.dp, bottom = 4.dp) // 오른쪽으로 이동
            )
            BusinessCardItem(icon = R.drawable.ic_call, text = "01057540000")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "기업 또는 단체명",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
                modifier = Modifier.padding()
                    .padding(start = 8.dp, bottom = 4.dp) // 오른쪽으로 이동
            )
            BusinessCardItem(icon = R.drawable.ic_groupname, text = "홍얼홍얼")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "부서/직책",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
                modifier = Modifier.padding()
                    .padding(start = 8.dp, bottom = 4.dp) // 오른쪽으로 이동
            )
            BusinessCardItem(icon = R.drawable.ic_role, text = "백엔드개발자")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "이메일",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
                modifier = Modifier.padding()
                    .padding(start = 8.dp, bottom = 4.dp) // 오른쪽으로 이동
            )
            BusinessCardItem(icon = R.drawable.ic_email, text = "xxx@stu.kmu.ac.kr")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "SNS",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
                modifier = Modifier.padding()
                    .padding(start = 8.dp, bottom = 4.dp) // 오른쪽으로 이동
            )
            BusinessCardItem(icon = R.drawable.ic_insta, text = "@hongeol.hongeol")
            BusinessCardItem(icon = R.drawable.ic_kakao, text = "hongeol")
            BusinessCardItem(icon = R.drawable.ic_facebook, text = "honghonghong")
            BusinessCardItem(icon = R.drawable.ic_twitter, text = "@hongeol")
            BusinessCardItem(icon = R.drawable.ic_blog, text = "hongeoul")

            // "수정하기" 버튼도 스크롤 내용의 맨 아래에 포함
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*수정으로 이동*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422)),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "수정하기",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun BusinessCardItem(icon: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 11.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(27.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = text,
            fontSize = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModifyBusinessCardScreenPreview() {
    FilQTheme {
        BusinessCardMetaDataScreen()
    }
}

