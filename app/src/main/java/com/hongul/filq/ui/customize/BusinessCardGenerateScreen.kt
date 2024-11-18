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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hongul.filq.R
import com.hongul.filq.ui.customize.page.BasicInformationPage
import com.hongul.filq.ui.customize.page.BusinessCardPhotoGuidePage
import com.hongul.filq.ui.customize.page.BusinessCardPreviewPage
import com.hongul.filq.ui.customize.page.BusinessCardTemplatePage
import com.hongul.filq.ui.customize.page.ChangeTextColorPage
import com.hongul.filq.ui.customize.page.LetterPositionPage
import com.hongul.filq.ui.customize.page.OrganizationInfoPage
import com.hongul.filq.ui.customize.page.PlusSnsPage
import com.hongul.filq.ui.customize.page.RegisterBusinessCardPage
import com.hongul.filq.ui.customize.page.SelectBusinessCardStylePage
import com.hongul.filq.ui.customize.page.SelectPhotoPage
import com.hongul.filq.ui.customize.page.SocialInfoPage
import com.hongul.filq.ui.customize.page.StartPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardGenerateScreen(navigator: NavHostController) {

    val ps = rememberPagerState(initialPage = 0) { 15}//총 페이지 개수 바꾸기
    val scope = rememberCoroutineScope()

    var title by remember { mutableStateOf("")}
    var selectedTemplateImageRes by remember { mutableStateOf<Int?>(null) }

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
            9 -> "글자 색 바꾸기"
            10 -> "명함 등록"
            8 -> "글자 위치 바꾸기"
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
                            else if(title == "글자 위치 바꾸기"){
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 100.dp)
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
                    onNavigateToBusinessCard = { scope.launch { ps.animateScrollToPage(11) } },
                    onNavigateToPersonalCard = { scope.launch { ps.animateScrollToPage(7) } }
                )
                7-> SelectPhotoPage(
                    onNext = { scope.launch { ps.animateScrollToPage(8) } }
                )

                8-> LetterPositionPage(
                    onNext = { scope.launch { ps.animateScrollToPage(9) } },
                    onBack = { scope.launch { ps.animateScrollToPage(7) } }
                )

                9 -> ChangeTextColorPage(
                    onNextClick = { scope.launch { ps.animateScrollToPage(10) } },
                    onColorSelected = { selectedColor ->
                        Log.d("ChangeTextColorPage", "Selected color: $selectedColor")
                    }
                )
                10 -> RegisterBusinessCardPage(
                    onCompleteClick = { scope.launch { ps.animateScrollToPage(0) } }
                )

                11 -> {
                    title = "명함 생성"
                    BusinessCardTemplatePage(
                        templates = listOf(
                            R.drawable.bc1,
                            R.drawable.bc2,
                            R.drawable.bc3,
                            R.drawable.bc4,
                            R.drawable.bc5,
                            R.drawable.bc6,
                            R.drawable.bc7,
                            R.drawable.bc8,
                            R.drawable.bc9,
                            R.drawable.bc10,
                            R.drawable.bc11,
                            R.drawable.bc12,
                            R.drawable.bc13,
                            R.drawable.bc14,
                            R.drawable.bc15,
                            R.drawable.bc16,
                            R.drawable.bc17,
                            R.drawable.bc18,
                            R.drawable.bc19,
                            R.drawable.bc20,
                            R.drawable.bc21,
                            R.drawable.bc22,
                            R.drawable.bc23,
                            R.drawable.bc24,
                            R.drawable.bc25,
                            R.drawable.bc26,
                            R.drawable.bc27,
                            R.drawable.bc28,
                        ),
                        onTemplateSelected = { selectedTemplate ->
                            scope.launch {
                                ps.animateScrollToPage(12) // 다음 페이지로 이동
                                selectedTemplateImageRes = selectedTemplate // 선택된 템플릿 저장
                            }
                        },
                        onBackClick = {
                            scope.launch {
                                ps.animateScrollToPage(10) // 이전 페이지로 이동
                            }
                        }
                    )
                }
                12 -> {
                    title = "명함 생성"
                    selectedTemplateImageRes?.let { imageRes -> // 선택된 이미지 리소스가 있을 경우
                        BusinessCardPreviewPage(
                            backgroundRes = imageRes, // 선택된 템플릿 이미지 리소스 전달
                            onCompleteClick = {
                                scope.launch {
                                    Log.d("BusinessCardPreviewScreen", "완료 버튼 클릭됨")
                                    ps.animateScrollToPage(0) // 완료 버튼 클릭 시 첫 페이지로 이동
                                }
                            },
                            onBackClick = {
                                scope.launch {
                                    ps.animateScrollToPage(11) // 이전 페이지로 이동
                                }
                            }
                        )
                    } ?: Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center // 중앙에 텍스트 표시
                    ) {
                        // 선택된 이미지 리소스가 없을 때
                        Text(
                            text = "템플릿이 선택되지 않았습니다.\n다시 시도해주세요.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}