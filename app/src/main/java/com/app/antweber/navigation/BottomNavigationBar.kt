package com.app.antweber.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.antweber.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier.height(60.dp)
            ) {
                val currentRoute by navController.currentBackStackEntryAsState()

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "Home",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(vertical = 1.dp),
                            tint = if (currentRoute?.destination?.route == "home") Color(0xFFCF497E) else Color.Gray
                        )
                    },
                    modifier = Modifier.background(Color.White),
                    selected = currentRoute?.destination?.route == "home",
                    onClick = { navController.navigate("home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFCF497E),
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.antlogo),
                            contentDescription = "TestScreen",
                            modifier = Modifier
                                .size(27.dp)
                                .padding(vertical = 1.dp),
                            tint = if (currentRoute?.destination?.route == "testScreen") Color(
                                0xFFCF497E
                            ) else Color.Gray
                        )
                    },
                    modifier = Modifier.background(Color.White),
                    selected = currentRoute?.destination?.route == "testScreen",
                    onClick = { navController.navigate("testScreen") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFCF497E),
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
    }
}
