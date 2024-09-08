package com.app.antweber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.app.antweber.navigation.NavGraph
import com.app.antweber.navigation.BottomNavigationBar
import com.app.antweber.ui.theme.AntWeberTheme
import com.app.antweber.ui.theme.SplashScreen
import com.app.antweber.ui.viewmodel.HomeViewModel
import com.app.antweber.ui.viewmodel.ImageDetailViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AntWeberTheme {

                var showSplash by remember { mutableStateOf(true) }


                LaunchedEffect(Unit) {
                    delay(2300) //
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {

                    val navController = rememberNavController()
                    val homeViewModel: HomeViewModel = viewModel()
                    val imageDetailViewModel: ImageDetailViewModel = viewModel()

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = { BottomNavigationBar(navController) }
                    ) { innerPadding ->
                        NavGraph(
                            navController = navController,
                            homeViewModel = homeViewModel,
                            imageDetailViewModel = imageDetailViewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
