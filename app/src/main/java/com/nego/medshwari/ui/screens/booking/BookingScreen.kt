package com.nego.medshwari.ui.screens.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.nego.medshwari.R
import com.nego.medshwari.ui.screens.splash.SplashScreen

@Composable
fun BookingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Bookings",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        // Placeholder image for booking
        Image(
            painter = painterResource(id = R.drawable.book),
            contentDescription = "Bookings",
            modifier = Modifier
                .size(180.dp)
        )

        Text(
            text = "You haven't booked any appointment yet.",
            fontSize = 16.sp
        )

        Button(onClick = { /*TODO: Navigate to booking*/ }) {
            Text("Book Now")
        }
    }
}
