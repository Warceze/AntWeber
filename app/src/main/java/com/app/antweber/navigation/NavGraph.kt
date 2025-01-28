package com.app.antweber.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.antweber.ui.components.HomeScreen
import com.app.antweber.ui.components.ImageDetailScreen
import com.app.antweber.ui.viewmodel.HomeViewModel
import com.app.antweber.ui.viewmodel.ImageDetailViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    imageDetailViewModel: ImageDetailViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                viewModel = homeViewModel,
                onImageClick = { imageUrl ->
                    imageDetailViewModel.setImageUrl(imageUrl)
                    navController.navigate("imageDetail")
                }
            )
        }
        composable("imageDetail") {
            ImageDetailScreen(
                viewModel = imageDetailViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
