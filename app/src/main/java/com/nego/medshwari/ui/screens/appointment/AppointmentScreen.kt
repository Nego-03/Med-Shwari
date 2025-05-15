package com.nego.medshwari.ui.screens.appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nego.medshwari.navigation.ROUT_APPOINTMENT
import com.nego.medshwari.navigation.ROUT_DASHBOARD
import com.nego.medshwari.navigation.ROUT_PATIENT
import com.nego.medshwari.ui.theme.newBlue
import com.nego.medshwari.ui.theme.newLiBlue

@Composable
fun AppointmentScreen(navController: NavController){

    var selectedTab by remember { mutableStateOf("Appointments") }

    Scaffold(
        bottomBar = {
            NavigationBar (
                modifier = Modifier.background(newBlue),
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") },
                    selected = selectedTab == "Dashboard",
                    onClick = { navController.navigate(ROUT_DASHBOARD)  }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Appointments") },
                    label = { Text("Appointments") },
                    selected = selectedTab == "Appointments",
                    onClick = { navController.navigate(ROUT_APPOINTMENT)  }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == "Profile",
                    onClick = { navController.navigate(ROUT_PATIENT) }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .background(newLiBlue)
        ) {
            Text(
                text = "Your Appointments",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(5) { index ->
                    AppointmentCard(
                        doctorName = "Dr. Jane Smith",
                        date = "May ${10 + index}, 2025",
                        time = "10:00 AM",
                        hospital = "MedShwari Hospital"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun AppointmentCard(
    doctorName: String,
    date: String,
    time: String,
    hospital: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Doctor: $doctorName", fontWeight = FontWeight.SemiBold)
            Text("Date: $date")
            Text("Time: $time")
            Text("Location: $hospital")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AppointmentScreenPreview(){

    AppointmentScreen(rememberNavController())
}