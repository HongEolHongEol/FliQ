package com.hongul.filq.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.ui.theme.FilQTheme
import com.hongul.filq.R
import kotlinx.serialization.Serializable
import androidx.compose.foundation.shape.RoundedCornerShape

@Serializable
data class BusinessCardMetaDataRoute(
    val cardId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardMetaDataScreen() {

    // 세로방향
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("명함 편집", fontSize = 20.sp) },
                navigationIcon = { IconButton(onClick = {}){
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft,null)
                } }
            )
        }
    ){ innerPadding->
        Box(modifier=Modifier.padding(innerPadding)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                ) {

                // 더 간격 띄우기
                Spacer(modifier = Modifier.height(23.dp))

                // 각 항목(편집 ㄱㄴ하게해야함 수정 누르면)
                BusinessCardItem(icon = R.drawable.ic_person, text = "홍알홍알")
                BusinessCardItem(icon = R.drawable.ic_group, text = "캡스톤(1)")
                BusinessCardItem(icon = R.drawable.ic_star, text = "홍얼홍얼")
                BusinessCardItem(icon = R.drawable.ic_mic, text = "010 0000 0000")
                BusinessCardItem(icon = R.drawable.ic_pencil, text = "비지니스명함")
                BusinessCardItem(icon = R.drawable.ic_pencil, text = "xxx@stu.kmu.ac.kr")
                BusinessCardItem(icon = R.drawable.ic_link, text = "@hongeol.hongeol")
                BusinessCardItem(icon = R.drawable.ic_link, text = "hong")
                BusinessCardItem(icon = R.drawable.ic_link, text = "//fliq")
                BusinessCardItem(icon = R.drawable.ic_link, text = "http://digital.profile")
                BusinessCardItem(icon = R.drawable.ic_link, text = "eofk-dfdr")

                Spacer(modifier = Modifier.weight(1f)) // 빈 공간 남아있는 공간 전부

                // 수정하기
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
}

@Composable
fun BusinessCardItem(icon: Int, text: String) {
    Row(
        //수직 정렬
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
        //아이콘이랑 텍스트 거리
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = text,
            fontSize = 17.sp
        )
    }
}

//미리보기 코드
@Preview(showBackground = true)
@Composable
fun ModifyBusinessCardScreenPreview() {
    FilQTheme {
        BusinessCardMetaDataScreen()
    }
}