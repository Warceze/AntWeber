package com.app.antweber.ui.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.antweber.model.UnsplashResponse
import com.app.antweber.ui.viewmodel.HomeViewModel
import com.app.antweber.R
import com.app.antweber.ui.theme.myCustomFont
import com.app.antweber.ui.theme.NoImagesPlaceholder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onImageClick: (String) -> Unit
) {
    val images by viewModel.images.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.loadImages() })
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(isRefreshing) {
        Log.d("HomeScreen", "isRefreshing: $isRefreshing")
        if (isRefreshing) {
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )

        } else {
            rotation.snapTo(0f)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
            )
            TabRow(
                tabs = listOf("New", "Popular"),
                onTabSelected = { category ->
                    viewModel.loadImages(orderBy = if (category == "New") "latest" else "popular")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(bottom = 16.dp)
            )

            if (images.isEmpty()) {
                Spacer(modifier = Modifier.height(65.dp))
                NoImagesPlaceholder()

            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(bottom = 15.dp)
                ) {
                    items(images) { image ->
                        ImageItem(
                            image = image,
                            onClick = {
                                Log.d("HomeScreen", "Image clicked: ${image.urls.full}")
                                onImageClick(image.urls.full)
                            }
                        )
                    }
                }

            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.padding(top = 150.dp)
        )
    }
}

@Composable
fun ImageItem(image: UnsplashResponse, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(image.urls.small),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(35.dp))
            .background(Color.White)
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { newValue -> searchQuery = newValue },
            placeholder = { Text("Search") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_magn_glass),
                    contentDescription = "Search icon",
                    modifier = Modifier
                        .padding(start = 14.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp, vertical = 12.dp)
                .clip(RoundedCornerShape(35.dp)),
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEEEEEF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }
}

@Composable
fun TabRow(
    tabs: List<String>,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        indicator = { tabPositions ->
            val currentTabPosition = tabPositions[selectedTabIndex]

            if (selectedTabIndex == 0) {
                Box(
                    modifier = Modifier
                        .offset(x = (-173).dp, y = 48.dp)
                        .width(currentTabPosition.width)
                        .background(Color(0xFFCF497E))
                )
            } else {
                Box(
                    modifier = Modifier
                        .offset(x = 173.dp, y = 48.dp)
                        .width(currentTabPosition.width)
                        .background(Color(0xFFCF497E))
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        title,
                        color = if (selectedTabIndex == index) Color.Black else Color.Gray,
                        fontFamily = myCustomFont
                    )
                },
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(title)
                },
                modifier = Modifier
                    .padding(1.dp)
                    .background(Color.White)
            )
        }
    }
}
