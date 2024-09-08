package com.app.antweber.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import coil.compose.rememberAsyncImagePainter
import com.app.antweber.ui.theme.myCustomFont
import com.app.antweber.ui.viewmodel.ImageDetailViewModel
import com.app.antweber.ui.viewmodel.ImageState

@Composable
fun ImageDetailScreen(
    viewModel: ImageDetailViewModel,
    onBack: () -> Unit
) {
    val imageState by viewModel.imageState.collectAsState()

    Scaffold(
        topBar = {
            CustomTopBarWithCard(onBack = onBack)
        },
        modifier = Modifier.background(Color.White)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            when (imageState) {
                is ImageState.Loading -> {
                    CircularProgressIndicator(
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(80.dp)
                            .size(40.dp)
                    )
                }
                is ImageState.Success -> {
                    Image(
                        painter = rememberAsyncImagePainter((imageState as ImageState.Success).url),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                    )
                }
                is ImageState.Error -> {
                    Text(
                        text = "Failed to load image",
                        color = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}


@Composable
fun CustomTopBarWithCard(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Image Details", fontFamily = myCustomFont)
        }
    }
}
