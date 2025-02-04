package com.example.staticuserprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(){
    // Control the Snackbar display
    val snackbarHostState = remember { SnackbarHostState() }
    // routine scope for triggering asynchronous operations
    val coroutineScope = rememberCoroutineScope()

    // Optimize the UI by 4o
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF0D1B2A), Color(0xFF1B263B)) // 4o helps me to pick a color for background
    )

    // Display the main content on the Screen and provide SnackbarHost
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Transparent
    ){
            innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture", // 4o teaches me to do so but i don't know why need a description
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Kane Lee",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
                )
            Spacer(modifier = Modifier.height(8.dp))
            // brief introduction
            Text(
                text = "I always want five more minutes of sleep every day. My dream is to sleep until the end of the world.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 24.dp),
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Follow Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Following") // learn from 4o
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                shape = RoundedCornerShape(50),
                        modifier = Modifier
                        .height(50.dp)
                        .width(160.dp)
            ) {
                Text("Follow", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(32.dp))
            // Additional Function (recent activities)
            Text(
                text = "Recent Activities",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RecentActivityCard(imageRes = R.drawable.recent_photo1)
                RecentActivityCard(imageRes = R.drawable.recent_photo2)
                RecentActivityCard(imageRes = R.drawable.recent_photo3)
            }
            }
        }
    }
}

@Composable
fun RecentActivityCard(imageRes: Int) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Recent Activity",
            modifier = Modifier.fillMaxSize()
        )
    }
}