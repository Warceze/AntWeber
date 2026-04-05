package com.app.antweber.ui.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.app.antweber.ui.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Alignment

@Composable
fun TestScreen(
    viewModel: HomeViewModel,
    onBack: () -> Unit,
    onImageClick: (String) -> Unit
) {
    val images by viewModel.images.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val rotation = remember { Animatable(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(images, key = { it.id }) { image ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        ImageItem(
                            image = image,
                            onClick = {
                                onImageClick(image.urls.regular)
                            },
                            modifier = Modifier.size(105.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    ) {
                        Text(
                            text = "Name - ${image.description ?: "Untitled"}",
                            color = Color.Black
                        )
                        Text(
                            text = "Author - ${image.user.name}",
                            color = Color.Gray
                        )
                        Text(
                            text = "Likes : ${image.likes}",
                            color = Color(0xFFD4596E)
                        )
                    }
                }

            }

        }

    }


}

