package com.hongul.filq.ui.customize

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hongul.filq.R
import com.hongul.filq.ui.customize.page.StartPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardGenerateScreen(navigator: NavHostController) {

    val ps = rememberPagerState(initialPage = 0) { 10 }//총 페이지 개수 바꾸기
    val scope = rememberCoroutineScope()

    var title by remember { mutableStateOf("")}
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        if (ps.currentPage == 0) {
                            navigator.popBackStack()
                        }
                        else{
                            scope.launch {
                                ps.animateScrollToPage(ps.currentPage-1)
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "뒤로 가기"
                        )
                    }
                },
                title = { Text(title) },
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (ps.currentPage) {
                0 -> {
                    title = ""
                    StartPage(
                        onCreate = {
                            scope.launch {
                                ps.animateScrollToPage(1)
                            }
                        },
                        onUpload = {

                        }
                    )
                }
                1-> {
                    title ="명함 생성"

                }
            }
        }
    }
}
