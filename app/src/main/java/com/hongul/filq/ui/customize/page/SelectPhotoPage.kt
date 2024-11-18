package com.hongul.filq.ui.customize.page

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.hongul.filq.R
import java.io.IOException

fun saveImageToGallery(context: Context, bitmap: Bitmap) {
    try {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "${System.currentTimeMillis()}.jpg") // 파일 이름
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // MIME 타입
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/FliQ") // 저장 경로
        }

        val uri: Uri? = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            context.contentResolver.openOutputStream(it)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
            }

            // MediaScannerConnection 호출
            MediaScannerConnection.scanFile(
                context,
                arrayOf(it.toString()),
                arrayOf("image/jpeg")
            ) { path, scannedUri ->
                println("Scanned file path: $path")
                println("Scanned URI: $scannedUri")
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestGalleryPermission(onPermissionGranted: () -> Unit) {
    val permissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        } else {
            onPermissionGranted()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SelectPhotoPage(onNext: () -> Unit) {
    var selectedImage by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    // 권한 요청 처리
    val permissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    // 권한 요청 상태를 확인하는 변수
    val isPermissionGranted = permissionState.status.isGranted

    // 권한이 허용되지 않으면 권한 요청
    LaunchedEffect(permissionState.status) {
        if (!isPermissionGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    // Register for activity result to pick an image
    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                // Convert URI to Bitmap
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    selectedImage = bitmap
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            // Display selected image or default image (add_business_card.png)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                selectedImage?.let { image ->
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                } ?: run {
                    Image(
                        painter = painterResource(id = R.drawable.basic_business_card),
                        contentDescription = "Add Business Card",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                // Bottom-left text
                Text(
                    text = "홍얼홍얼",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.BottomStart)
                        .offset(x = 10.dp, y = -90.dp)
                )
                Text(
                    text = "캡스톤(1)",
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.BottomStart)
                        .offset(x = 10.dp, y = -70.dp)
                )
                Text(
                    text = "010.0000.0000",
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.BottomStart)
                        .offset(x = 10.dp, y = -50.dp)
                )
                Text(
                    text = "xxx@stu.kmu.ac.kr",
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.BottomStart)
                        .offset(x = 10.dp, y = -30.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 5.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "사진을 직접 등록하여,\n 배경을 꾸며보세요",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 30.dp)
                )

                Button(
                    onClick = {
                        // Open the image picker when button is clicked
                        imagePickerLauncher.launch("image/*")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(1.dp, Color(0xFF125422), RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xE5E5E5))
                ) {
                    Text(text = "사진첩에서 가져오기", color = Color(0xFF125422), fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Save button to save the selected image to gallery
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = {
                        // Save image to gallery
                        selectedImage?.let { saveImageToGallery(context, it) }
                        onNext() // 이동 콜백 호출
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
                ) {
                    Text(text = "완료", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}