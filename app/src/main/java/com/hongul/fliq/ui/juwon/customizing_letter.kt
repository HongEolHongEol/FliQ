package com.hongul.fliq.ui.juwon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.fliq.R
//import com.hongul.fliq.ui.theme.BusinessCardTheme
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily


@Composable
fun BusinessCardScreen(modifier: Modifier = Modifier) {
    var selectedText by remember { mutableStateOf<String?>(null) }
    var name by remember { mutableStateOf("홍얼홍얼") }
    var fontSize by remember { mutableStateOf(16.sp) }
    var fontWeight by remember { mutableStateOf(FontWeight.Normal) }
    var selectedTab by remember { mutableStateOf("글자") }

    var nameFontSize by remember { mutableStateOf(16.sp) }
    var nameFontWeight by remember { mutableStateOf(FontWeight.Normal) }
    var phoneFontSize by remember { mutableStateOf(16.sp) }
    var phoneFontWeight by remember { mutableStateOf(FontWeight.Normal) }
    var emailFontSize by remember { mutableStateOf(16.sp) }
    var emailFontWeight by remember { mutableStateOf(FontWeight.Normal) }
    var sloganFontSize by remember { mutableStateOf(16.sp) }
    var sloganFontWeight by remember { mutableStateOf(FontWeight.Normal) }

    var nameFontFamily by remember { mutableStateOf<FontFamily>(FontFamily.Default) }
    var phoneFontFamily by remember { mutableStateOf<FontFamily>(FontFamily.Default) }
    var emailFontFamily by remember { mutableStateOf<FontFamily>(FontFamily.Default) }
    var sloganFontFamily by remember { mutableStateOf<FontFamily>(FontFamily.Default) }

    var nameFontColor by remember { mutableStateOf(Color.Black) }
    var phoneFontColor by remember { mutableStateOf(Color.Black) }
    var emailFontColor by remember { mutableStateOf(Color.Black) }
    var sloganFontColor by remember { mutableStateOf(Color.Black) }

    var alphaValue by remember { mutableStateOf(1f) } // 전역 선언
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        // 명함 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 50.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    val textStyle = { key: String ->
                        if (selectedText == key) {
                            TextStyle(fontSize = fontSize, fontWeight = fontWeight) // 선택된 애만 스타일 적용
                        } else {
                            TextStyle.Default
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = name,
                            style = TextStyle(
                                fontSize = nameFontSize,
                                fontWeight = nameFontWeight,
                                fontFamily = nameFontFamily,
                                color = nameFontColor
                            ),

                            modifier = Modifier.clickable { selectedText = "name" }
                        )
                        Text(
                            text = "+82)10.0000.0000",
                            style = TextStyle(
                                fontSize = phoneFontSize,
                                fontWeight = phoneFontWeight,
                                fontFamily = phoneFontFamily,
                                color = phoneFontColor
                            ),
                            modifier = Modifier.clickable { selectedText = "phone" }
                        )
                        Text(
                            text = "xxx@stu.kmu.ac.kr",
                            style = TextStyle(
                                fontSize = emailFontSize,
                                fontWeight = emailFontWeight,
                                fontFamily = emailFontFamily,
                                color = emailFontColor
                            ),
                            modifier = Modifier.clickable { selectedText = "email" }
                        )
                        Text(
                            text = "Wishlist _ can't be blue",
                            style = TextStyle(
                                fontSize = sloganFontSize,
                                fontWeight = sloganFontWeight,
                                fontFamily = sloganFontFamily,
                                color = sloganFontColor
                            ),
                            modifier = Modifier.clickable { selectedText = "slogan" }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 8.dp)
                        .align(Alignment.Bottom)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_qr),
                        contentDescription = "QR Code",
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
        // 네모 박스 설정 Ui
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(8.dp) ,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TabButton("글자", painterResource(id = R.drawable.ic_text), selectedTab) { selectedTab = "글자" }
                    TabButton("배경", painterResource(id = R.drawable.ic_background), selectedTab) { selectedTab = "배경" }
                    TabButton("아이콘", painterResource(id = R.drawable.ic_icon), selectedTab) { selectedTab = "아이콘" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (selectedTab) {
                    "글자" -> {
                        val currentFontSize = when (selectedText) {
                            "name" -> nameFontSize.value.toInt()
                            "phone" -> phoneFontSize.value.toInt()
                            "email" -> emailFontSize.value.toInt()
                            "slogan" -> sloganFontSize.value.toInt()
                            else -> 16
                        }

                        val currentFontWeight = when (selectedText) {
                            "name" -> nameFontWeight
                            "phone" -> phoneFontWeight
                            "email" -> emailFontWeight
                            "slogan" -> sloganFontWeight
                            else -> FontWeight.Normal
                        }

                        FontSettings(
                            fontSize = currentFontSize,
                            onFontSizeChange = { newSize ->
                                when (selectedText) {
                                    "name" -> nameFontSize = newSize.sp
                                    "phone" -> phoneFontSize = newSize.sp
                                    "email" -> emailFontSize = newSize.sp
                                    "slogan" -> sloganFontSize = newSize.sp
                                }
                            },
                            fontWeight = currentFontWeight,
                            onFontWeightChange = { newWeight ->
                                when (selectedText) {
                                    "name" -> nameFontWeight = newWeight
                                    "phone" -> phoneFontWeight = newWeight
                                    "email" -> emailFontWeight = newWeight
                                    "slogan" -> sloganFontWeight = newWeight
                                }
                            },
                            onFontFamilyChange = { selectedFont ->
                                when (selectedText) {
                                    "name" -> nameFontFamily = selectedFont
                                    "phone" -> phoneFontFamily = selectedFont
                                    "email" -> emailFontFamily = selectedFont
                                    "slogan" -> sloganFontFamily = selectedFont
                                }
                            },
                            fontMap = fontMap,
                            isEnabled = selectedText != null,
                        )
                        Spacer(modifier = Modifier.height(24.dp)) // 폰트 설정과 색상 사이 간격

                        val sectionTitleStyle = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = pretendard
                        )
                        Text(
                            text = "색상",
                            style = sectionTitleStyle,
                            modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
                        )
                        var alphaValue by remember { mutableStateOf(1f) } // 초기 투명도 100%
                        ColorPicker(
                            colors = listOf( Color.Red, Color.Blue, Color.Green, Color.Gray,
                                Color(0xFFFFC0CB), // Pink
                                Color(0xFFFFD700)  // Gold
                            ),
                            onColorSelected = { selectedColor ->
                                val colorWithAlpha = selectedColor.copy(alpha = alphaValue)
                                when (selectedText) {
                                    "name" -> nameFontColor = colorWithAlpha
                                    "phone" -> phoneFontColor = colorWithAlpha
                                    "email" -> emailFontColor = colorWithAlpha
                                    "slogan" -> sloganFontColor = colorWithAlpha
                                }
                            }
                        )
                        Text(
                            text = "투명도",
                            style = sectionTitleStyle,
                            modifier = Modifier.padding(start = 24.dp, top = 8.dp, bottom = 0.dp)
                        )
                        val greenColor = Color(0xFF95C88A)

                        Slider(
                            value = alphaValue,
                            onValueChange = { newAlpha ->
                                alphaValue = newAlpha
                                val updateColor: (Color) -> Color = { it.copy(alpha = alphaValue) }
                                when (selectedText) {
                                    "name" -> nameFontColor = updateColor(nameFontColor)
                                    "phone" -> phoneFontColor = updateColor(phoneFontColor)
                                    "email" -> emailFontColor = updateColor(emailFontColor)
                                    "slogan" -> sloganFontColor = updateColor(sloganFontColor)
                                }
                            },
                            valueRange = 0f..1f,
                            steps = 9, // 10단계
                            modifier = Modifier.padding(horizontal = 24.dp),
                            colors = SliderDefaults.colors(
                                thumbColor = greenColor,
                                activeTrackColor = greenColor,
                                inactiveTrackColor = greenColor.copy(alpha = 0.3f), // 흐리게
                                activeTickColor = Color.Transparent,
                                inactiveTickColor = Color.Transparent
                            )
                        )
                    }
                    "배경" -> BackgroundSettings()
                    "아이콘" -> IconSettings()
                    else -> {}

                }

            }
        }
    }
}


@Composable
fun ColorPicker(colors: List<Color>, onColorSelected: (Color) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(color, shape = CircleShape)
                    .clickable { onColorSelected(color) }
            )
        }
    }
}

@Composable
fun TabButton(label: String, icon: Painter, selectedTab: String, onClick: () -> Unit) {
    val isSelected = selectedTab == label

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .size(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF95C88A) else Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = if (isSelected) Color.White else Color.Gray,
                modifier = Modifier.size(65.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = label,
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 12.sp
            )
        }
    }
}
val pretendard = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.Bold)
)
val roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold)
)
val notoSans = FontFamily(
    Font(R.font.notosans_regular, FontWeight.Normal),
    Font(R.font.notosans_bold, FontWeight.Bold)
)

val fontMap = mapOf(
    "Pretendard" to pretendard,
    "Roboto" to roboto,
    "Noto Sans" to notoSans
)
// 🔹 폰트 설정 UI
@Composable
fun FontSettings(
    fontSize: Int,
    onFontSizeChange: (Int) -> Unit,
    fontWeight: FontWeight,
    onFontWeightChange: (FontWeight) -> Unit,
    onFontFamilyChange: (FontFamily) -> Unit,
    fontMap: Map<String, FontFamily>,
    isEnabled: Boolean
) {
    val sectionTitleStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = pretendard
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text("폰트",  style = sectionTitleStyle)
        DropdownMenuSample(
            items = fontMap.keys.toList(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            enabled = isEnabled
        ) { selectedLabel ->
            fontMap[selectedLabel]?.let {
                onFontFamilyChange(it)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f) // 동일 폭
            ) {
                Text("크기",  style = sectionTitleStyle)
                DropdownMenuSample(
                    items = listOf("12px", "14px", "16px", "18px", "20px"),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    onFontSizeChange(it.replace("px", "").toInt())
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f) // 동일 폭
            ) {
                Text("굵기",  style = sectionTitleStyle)
                DropdownMenuSample(
                    items = listOf("Regular", "Bold"),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    onFontWeightChange(if (it == "Bold") FontWeight.Bold else FontWeight.Normal)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuSample(
    items: List<String>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(items[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (enabled) expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedText = selectionOption
                        onItemSelected(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

// 🔹 배경 설정 (예제)
@Composable
fun BackgroundSettings() {
    Text("배경 설정 기능 추가 예정")
}

// 🔹 아이콘 설정 (예제)
@Composable
fun IconSettings() {
    Text("아이콘 설정 기능 추가 예정")
}