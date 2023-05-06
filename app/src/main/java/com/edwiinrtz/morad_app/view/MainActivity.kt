package com.edwiinrtz.morad_app.view

import android.Manifest
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwiinrtz.morad_app.view.ui.screens.DashboardScreen
import com.edwiinrtz.morad_app.view.ui.screens.LoginScreen
import com.edwiinrtz.morad_app.view.ui.screens.SigninScreen
import com.edwiinrtz.morad_app.view.ui.theme.MoradAppTheme
import com.edwiinrtz.morad_app.viewmodel.DashboardViewModel
import com.edwiinrtz.morad_app.viewmodel.LoginViewModel
import com.edwiinrtz.morad_app.viewmodel.SigninViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


var startDestination = ""
private lateinit var auth: FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        val signinViewModel = SigninViewModel(auth)
        val loginViewModel = LoginViewModel(auth)
        val dashboardViewModel = DashboardViewModel(auth)


        setContent {
            val navController = rememberNavController()
            val logged = userAlreadyLoged()
            MoradAppTheme {
                startDestination = "login"
                if (logged) {
                    startDestination = "dashboard"
                }

                NavHost(navController = navController, startDestination = startDestination) {

                    composable("login") {
                        LoginScreen(navController, loginViewModel, LocalContext.current)
                    }

                    composable("signin") {
                        SigninScreen(navController, signinViewModel, LocalContext.current)
                    }

                    composable("dashboard") {
                        dashboardViewModel.getUser()
                        dashboardViewModel.getMorada()
                        DashboardScreen(
                            navController = navController,
                            currentUser = auth.currentUser,
                            viewModel = dashboardViewModel
                        )
                    }

                }
            }
        }
    }


}

fun userAlreadyLoged(): Boolean {

    val currentUser = auth.currentUser
    if (currentUser != null) {
        return true
    }
    return false
}
