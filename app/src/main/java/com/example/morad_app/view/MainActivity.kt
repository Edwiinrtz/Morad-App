package com.example.morad_app.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.morad_app.view.ui.components.DashboardScreen

import com.example.morad_app.view.ui.screens.LoginScreen
import com.example.morad_app.view.ui.screens.SigninScreen
import com.example.morad_app.view.ui.theme.MoradAppTheme


var startDestination = ""
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            val navController = rememberNavController()
            val logged = userAlreadyLoged()
            MoradAppTheme {
                startDestination = "login"
                if (logged) {
                    //navigate to login
                    //LoginScreen()
                    startDestination = "dashboard"
                }
                NavHost(navController = navController, startDestination = startDestination) {
                    composable("login") { LoginScreen(navController) }
                    composable("signin") { SigninScreen(navController) }
                    composable("dashboard") { DashboardScreen() }

                }


                //navigate to dashboard
            }
        }
    }
    



}

fun userAlreadyLoged(): Boolean {
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