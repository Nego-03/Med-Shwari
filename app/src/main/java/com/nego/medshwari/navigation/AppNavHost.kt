package com.nego.medshwari.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nego.medshwari.data.UserDatabase
import com.nego.medshwari.repository.UserRepository
import com.nego.medshwari.ui.screens.appointment.AppointmentScreen
import com.nego.medshwari.ui.screens.auth.LoginScreen
import com.nego.medshwari.ui.screens.auth.RegisterScreen
import com.nego.medshwari.ui.screens.contact.ContactScreen
import com.nego.medshwari.ui.screens.dashboard.DashboardScreen
import com.nego.medshwari.ui.screens.prescription.PrescriptionScreen
import com.nego.medshwari.ui.screens.products.AddUserScreen
import com.nego.medshwari.ui.screens.products.EditUserScreen
import com.nego.medshwari.ui.screens.products.UserListScreen
import com.nego.medshwari.ui.screens.profile.PatientProfileScreen
import com.nego.medshwari.ui.screens.splash.SplashScreen
import com.nego.medshwari.viewmodel.AuthViewModel
import com.nego.medshwari.viewmodel.PatientViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    productViewModel: PatientViewModel = viewModel(),
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_APPOINTMENT) {
            AppointmentScreen(navController)
        }

        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }

        composable(ROUT_APPOINTMENT) {
            AppointmentScreen(navController)
        }

        composable(ROUT_PATIENT) {
            PatientProfileScreen(navController)
        }

        composable(ROUT_PRESCRIPTION) {
            PrescriptionScreen(navController)
        }




        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }

        // PRODUCTS
        composable(ROUT_ADD_PATIENT) {
            AddUserScreen(navController, productViewModel)
        }

        composable(ROUT_PATIENT_LIST) {
            UserListScreen(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PATIENT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("userId")
            if (productId != null) {
                EditUserScreen(userId = null, navController, productViewModel)
            }
        }

    }
}