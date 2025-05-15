package com.nego.medshwari.ui.screens.products

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nego.medshwari.viewmodel.PatientViewModel
import com.nego.medshwari.navigation.ROUT_ADD_PATIENT
import com.nego.medshwari.navigation.ROUT_PATIENT_LIST
import com.nego.medshwari.ui.theme.newLiBlue
import com.nego.medshwari.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen(navController: NavController, viewModel: PatientViewModel) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var showMenu by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add User", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("User List") },
                            onClick = {
                                navController.navigate(ROUT_PATIENT_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add User") },
                            onClick = {
                                navController.navigate(ROUT_ADD_PATIENT)
                                showMenu = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Product Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("User Name") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.name), contentDescription = "Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(10.dp))

                // Phone Number
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.phone), contentDescription = "Price") },
                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(16.dp))


                Spacer(modifier = Modifier.height(20.dp))

                // Add Product Button
                Button(
                    onClick = {
                            navController.navigate(ROUT_PATIENT_LIST)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Add User", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = newLiBlue,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PATIENT_LIST) },
            icon = { Icon(Icons.Default.Home, contentDescription = "User List") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_PATIENT) },
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add User") },
            label = { Text("Add") }
        )


        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_PATIENT) },
            icon = { Icon(painter = painterResource(R.drawable.profile), contentDescription = "") },
            label = { Text("Profile") }
        )
    }
}