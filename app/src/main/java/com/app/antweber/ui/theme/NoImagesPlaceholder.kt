package com.app.antweber.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.app.antweber.R

@Composable
fun NoImagesPlaceholder() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.ic_sorrymessage),
            contentDescription = "Sorry Message",
            modifier = Modifier
                .size(190.dp)
        )
    }
}