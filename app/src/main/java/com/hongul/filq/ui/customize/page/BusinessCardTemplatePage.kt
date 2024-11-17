package com.hongul.filq.ui.customize.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R

@Composable
fun BusinessCardTemplatePage(  templates: List<Int>,
                               onTemplateSelected: (Int) -> Unit,
                               onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 상단 제목
        Text(
            text = "명함 템플릿을\n선택하세요.",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // 그리드로 템플릿 표시
        // 템플릿 목록
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(templates) { template ->
                TemplateCard(
                    template = template,
                    onClick = { onTemplateSelected(template) }
                )
            }
        }
    }
}

@Composable
fun TemplateCard(template: Int, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.6f) // 명함의 일반적인 가로세로 비율 (가로가 더 긴 경우)
            .padding(4.dp)
    ) {
            // 템플릿 이미지
            Image(
                painter = painterResource(id = template),
                contentDescription = "Business Card Template",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }


@Composable
fun PreviewBusinessCardTemplatePage() {
    val templates = listOf(
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
    )

    BusinessCardTemplatePage(
        templates = templates,
        onTemplateSelected = { selectedTemplate ->
            // 선택한 템플릿 처리
            println("Selected template: $selectedTemplate")
        },
        onBackClick = {
            println("Back button clicked")
        }
    )
}

