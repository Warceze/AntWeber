package com.app.antweber.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.app.antweber.R

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.antlogo),
                contentDescription = "AntWeberLogo",
                modifier = Modifier
                    .width(160.dp)
                    .height(160.dp)
            )
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = "AntWeber",
                color = Color(0xFFD25671),
                fontSize = 29.sp,
                fontFamily = FontForSplash,
                fontWeight = FontWeight.Normal
            )
        }
    }
}