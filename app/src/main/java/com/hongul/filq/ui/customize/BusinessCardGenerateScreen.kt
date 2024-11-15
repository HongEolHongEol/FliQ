package com.hongul.filq.ui.customize

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hongul.filq.ui.customize.page.BasicInformationPage
import com.hongul.filq.ui.customize.page.OrganizationInfoPage
import com.hongul.filq.ui.customize.page.PlusSnsPage
import com.hongul.filq.ui.customize.page.SocialInfoPage
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
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center // 중앙 정렬
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.SemiBold, // 굵게 설정
                            fontSize = 18.sp, // 원하는 크기로 설정
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 121.dp)
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        HorizontalPager(
            state = ps,
            //count = 3, // 페이지 수 설정
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) { page ->
            when (page) {
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
                    title = "명함 생성"
                    BasicInformationPage(
                        onNext = {
                            scope.launch {
                                Log.d("BusinessCardGenerateScreen", "ps.animateScrollToPage(1) 호출됨")
                                ps.animateScrollToPage(2) // 다음 페이지로 이동
                            }
                        }
                    )
                }
                2 -> {
                    title = "명함 생성"
                    OrganizationInfoPage(
                        onNext = {
                            scope.launch {
                                Log.d("BusinessCardGenerateScreen", "OrganizationInfoPage -> 다음 페이지로 이동")
                                ps.animateScrollToPage(3) // 다음 페이지로 이동
                            }
                        }
                    )
                }
                3 -> {
                    title = "명함 생성"
                    SocialInfoPage(
                        onNext = {
                            scope.launch { ps.animateScrollToPage(4) } // 다음 페이지로 이동
                        }
                    )
                }
                4 -> {
                    title = "명함 생성"
                    PlusSnsPage(onBack = {
                        scope.launch { ps.animateScrollToPage(3) } // 이전 페이지로 이동
                    })
                }

            }
        }
    }
}

