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
import androidx.compose.runtime.LaunchedEffect
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
import com.hongul.filq.ui.customize.page.BusinessCardPhotoGuidePage
import com.hongul.filq.ui.customize.page.ChangeTextColorPage
import com.hongul.filq.ui.customize.page.OrganizationInfoPage
import com.hongul.filq.ui.customize.page.PlusSnsPage
import com.hongul.filq.ui.customize.page.RegisterBusinessCardPage
import com.hongul.filq.ui.customize.page.SelectBusinessCardStylePage
import com.hongul.filq.ui.customize.page.SocialInfoPage
import com.hongul.filq.ui.customize.page.StartPage
import com.hongul.filq.ui.customize.page.URLPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardGenerateScreen(navigator: NavHostController) {

    val ps = rememberPagerState(initialPage = 0) { 15}//총 페이지 개수 바꾸기
    val scope = rememberCoroutineScope()

    var title by remember { mutableStateOf("")}

    // ps.currentPage 값에 따라 title을 업데이트
    LaunchedEffect(ps.currentPage) {
        title = when (ps.currentPage) {
            0 -> ""
            1 -> "명함 사진 불러오기란?"
            2 -> "명함 생성"
            3 -> "명함 생성"
            4 -> "명함 생성"
            5 -> "명함 생성"
            6 -> "명함 생성"
            7 -> "글자 색 바꾸기"
            8 -> "명함 등록"
            else -> "명함 생성"
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        when (ps.currentPage) {
                            0 -> {
                                Log.d("Navigation", "Current page is 0. Exiting...")
                                navigator.popBackStack() // 첫 페이지에서 네비게이션 뒤로
                            }
                            1 -> {
                                Log.d("Navigation", "Navigating from page 1 to page 0")
                                scope.launch { ps.animateScrollToPage(0) } // "명함 사진 불러오기란?"에서 첫 페이지로
                            }
                            2-> {
                                scope.launch{ ps. animateScrollToPage(0)}
                            }
                            in 3..5 -> {
                                Log.d("Navigation", "Navigating back one page from page ${ps.currentPage}")
                                scope.launch { ps.animateScrollToPage(ps.currentPage - 1) } // 일반 뒤로가기
                            }
                            else -> {
                                Log.d("Navigation", "Navigating to page 5")
                                scope.launch { ps.animateScrollToPage(5) } // URL 페이지에서 SNS 추가로 이동
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
                            modifier = if (title == "글자 색 바꾸기") {
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 100.dp)
                            }
                                else if(title == "명함 사진 불러오기란?"){
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(start = 80.dp)
                            }
                                else{
                                    Modifier
                                    .fillMaxWidth()
                                    .padding(start = 121.dp)
                            }
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
                0 -> StartPage(
                    onCreate = { scope.launch { ps.animateScrollToPage(2) } },
                    onGuideClick = { scope.launch { ps.animateScrollToPage(1) } },
                    onUpload = {}
                )
                1 -> BusinessCardPhotoGuidePage(
                    onBackClick = { scope.launch { ps.animateScrollToPage(0) } }
                )
                2 -> BasicInformationPage(
                    onNext = { scope.launch { ps.animateScrollToPage(3) } }
                )
                3 -> OrganizationInfoPage(
                    onNext = { scope.launch { ps.animateScrollToPage(4) } }
                )
                4 -> SocialInfoPage(
                    onNext = { scope.launch { ps.animateScrollToPage(5) } }
                )
                5 -> PlusSnsPage(
                    onBack = { scope.launch { ps.animateScrollToPage(4) } },
                    navController = navigator
                )
                6 -> SelectBusinessCardStylePage(
                    onNavigateToBusinessCard = { scope.launch { ps.animateScrollToPage(7) } },
                    onNavigateToPersonalCard = { scope.launch { ps.animateScrollToPage(8) } }
                )
                7 -> ChangeTextColorPage(
                    onNextClick = { scope.launch { ps.animateScrollToPage(8) } },
                    onColorSelected = { selectedColor ->
                        Log.d("ChangeTextColorPage", "Selected color: $selectedColor")
                    }
                )
                8 -> RegisterBusinessCardPage(
                    onCompleteClick = { scope.launch { ps.animateScrollToPage(9) } }
                )

                }

            }
        }
    }

