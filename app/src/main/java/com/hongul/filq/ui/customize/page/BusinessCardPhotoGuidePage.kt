package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardPhotoGuidePage(onBackClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 제목
        Text(
            text = "명함 사진 불러오기",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 부제목
        Text(
            text = "여러분이 소지하고 있는 종이 명함을 촬영하여\n본 애플리케이션에 업로드 하면,\n자동으로 디지털 명함으로 변환됩니다.\n",
            fontSize = 16.sp,
            color = Color.Gray,
            lineHeight = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 1번 항목
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // 세로 중앙 정렬
        ) {
            Text(
                text = "1",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF125422),
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFEFFAF4), shape = RoundedCornerShape(50))
                    .wrapContentSize(Alignment.Center)
            )

            Spacer(modifier = Modifier.width(15.dp)) // 번호와 텍스트 간의 간격

            Text(
                text = "명함과 대비되는\n깔끔한 배경에서 촬영해주세요.",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth() // 남은 공간 채우기
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 위 이미지와 동그란 X, 체크 표시 추가
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 위 이미지
            Image(
                painter = painterResource(id = R.drawable.black_white), // 위쪽 이미지 리소스
                contentDescription = "명함 상단 이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(0.dp))

            // 동그란 X와 체크 표시
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF6F6F)), // 연한 배경색
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cross), // X 아이콘 리소스
                        contentDescription = "X 아이콘",
                        modifier = Modifier.size(24.dp),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF6FA8DC)), // 연한 배경색
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_check), // 체크 아이콘 리소스
                        contentDescription = "체크 아이콘",
                        modifier = Modifier.size(24.dp),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2번 항목
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "2",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF125422),
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFEFFAF4), shape = RoundedCornerShape(50))
                    .wrapContentSize(Alignment.Center)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "자동으로 인식해\n디지털 명함이 생성됩니다.",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 아래 이미지
        Image(
            painter = painterResource(id = R.drawable.bc2), // 아래쪽 이미지 리소스
            contentDescription = "명함 하단 이미지",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

