package com.nego.medshwari.ui.screens.products

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nego.medshwari.navigation.ROUT_ADD_PATIENT
import com.nego.medshwari.navigation.ROUT_PATIENT_LIST
import com.nego.medshwari.ui.theme.newLiBlue
import com.nego.medshwari.viewmodel.PatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(userId: Int?, navController: NavController, viewModel: PatientViewModel) {
    val context = LocalContext.current
    val userList by viewModel.allPatient.observeAsState(emptyList())

    val user = remember(userList) { userList.find { it.id == userId } }

    // Track state variables only when product is found
    var name by remember { mutableStateOf(user?.name ?: "") }
    var showMenu by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Product") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Home") },
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
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (user != null) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("User Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))


        }
    }
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar2(navController: NavController) {
    NavigationBar(
        containerColor = newLiBlue,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PATIENT_LIST) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "User List") },
            label = { Text("Users") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_PATIENT) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Add User") },
            label = { Text("Add") }
        )
    }
}
}