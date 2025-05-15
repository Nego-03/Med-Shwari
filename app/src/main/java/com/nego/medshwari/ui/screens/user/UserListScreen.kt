package com.nego.medshwari.ui.screens.products

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import com.nego.medshwari.viewmodel.PatientViewModel
import com.nego.medshwari.model.Patient
import com.nego.medshwari.model.User
import com.nego.medshwari.navigation.ROUT_ADD_PATIENT
import com.nego.medshwari.navigation.ROUT_EDIT_PATIENT
import com.nego.medshwari.navigation.ROUT_PATIENT_LIST
import com.nego.medshwari.navigation.editUserRoute
import com.nego.medshwari.ui.theme.newBlue
import com.nego.medshwari.ui.theme.newLiBlue
import java.io.IOException
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(navController: NavController, viewModel: PatientViewModel) {
    val userList by viewModel.allPatient.observeAsState(emptyList())
    var showMenu by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredUser = userList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("User", fontSize = 20.sp) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(newLiBlue),
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


                //Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    placeholder = { Text("Search user...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,  // Border color when focused
                        unfocusedBorderColor = Color.Gray, // Border color when not focused
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray
                    )
                )
            }
        },
        bottomBar = { BottomNavigationBar1(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun UserListScreen(navController: NavController, patient: Patient, viewModel: PatientViewModel) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                if (patient.id != 0) {
                    navController.navigate(ROUT_EDIT_PATIENT)
                }
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, newBlue.copy(alpha = 0.7f))
                        )
                    )
            )

            // User Info
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 60.dp)
            ) {
                Text(
                    text = patient.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

            }

            // Buttons (Message, Edit, Delete, Download PDF)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Message Doctor
                    OutlinedButton(
                        onClick = {
                            val smsIntent = Intent(Intent.ACTION_SENDTO)
                            context.startActivity(smsIntent)
                        },
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Message Doctor"
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(text = "Message Doctor")
                        }
                    }


                    // Delete Product
                    IconButton(
                        onClick = { viewModel.deletePatient(patient) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }

                }
            }
        }
    }
}



// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar1(navController: NavController) {
    NavigationBar(
        containerColor = newBlue,
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
    }
}