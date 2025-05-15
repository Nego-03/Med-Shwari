package com.nego.medshwari.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nego.medshwari.navigation.ROUT_APPOINTMENT
import com.nego.medshwari.navigation.ROUT_CONTACT
import com.nego.medshwari.navigation.ROUT_DASHBOARD
import com.nego.medshwari.navigation.ROUT_PATIENT
import com.nego.medshwari.navigation.ROUT_PRESCRIPTION
import com.nego.medshwari.ui.theme.newWhite

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Welcome, User 👋",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        DashboardCard(title = "Profile") {
            navController.navigate(ROUT_PATIENT)
        }

        DashboardCard(title = "Prescriptions") {
             navController.navigate(ROUT_PRESCRIPTION)
        }

        DashboardCard(title = "Doctor") {
            navController.navigate(ROUT_CONTACT)
        }

        DashboardCard(title = "Appointment") {
            navController.navigate(ROUT_APPOINTMENT)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo(ROUT_DASHBOARD) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}

@Composable
fun DashboardCard(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = newWhite)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UserScreen(){

    DashboardScreen(rememberNavController())
}