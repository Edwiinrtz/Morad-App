package com.edwiinrtz.morad_app.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwiinrtz.morad_app.view.ui.components.DashboardScreen
import com.edwiinrtz.morad_app.view.ui.screens.LoginScreen
import com.edwiinrtz.morad_app.view.ui.screens.SigninScreen
import com.edwiinrtz.morad_app.view.ui.theme.MoradAppTheme
import com.edwiinrtz.morad_app.viewmodel.LoginViewModel
import com.edwiinrtz.morad_app.viewmodel.SigninViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


var startDestination = ""
private lateinit var auth: FirebaseAuth

class MainActivity : ComponentActivity() {
    //val signinViewModel= SigninViewModel(auth)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        val signinViewModel = SigninViewModel(auth)
        val loginViewModel = LoginViewModel(auth)

        setContent {
            val navController = rememberNavController()
            val logged = userAlreadyLoged()
            MoradAppTheme {
                startDestination = "login"
                if (logged) {
                    startDestination = "dashboard"
                }
                NavHost(navController = navController, startDestination = startDestination) {
                    composable("login") { LoginScreen(navController, loginViewModel) }
                    composable("signin") {
                        SigninScreen(navController, signinViewModel)
                    }
                    composable("dashboard") { DashboardScreen(currentUser = auth.currentUser) }
                }
                //navigate to dashboard
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoradAppTheme {
        Greeting("Android")
    }
}