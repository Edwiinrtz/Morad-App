package com.edwiinrtz.morad_app.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwiinrtz.morad_app.model.Persona
import com.edwiinrtz.morad_app.view.ui.components.DashboardScreen
import com.edwiinrtz.morad_app.view.ui.screens.LoginScreen
import com.edwiinrtz.morad_app.view.ui.screens.SigninScreen
import com.edwiinrtz.morad_app.view.ui.theme.MoradAppTheme
import com.edwiinrtz.morad_app.viewmodel.SigninViewModel


var startDestination = ""

class MainActivity : ComponentActivity() {
    val signinViewModel= SigninViewModel()

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
                    composable("signin") { SigninScreen(navController, signinViewModel) }
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