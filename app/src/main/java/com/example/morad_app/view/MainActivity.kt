package com.example.morad_app.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.morad_app.view.ui.components.Input
import com.example.morad_app.view.ui.screens.LoginScreen
import com.example.morad_app.view.ui.theme.MoradAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val logged = userAlreadyLoged()
            MoradAppTheme {
                if(logged){
                    //navigate to dashboard
                }
                LoginScreen()

                //navigate to login

            }
        }
    }
}

fun userAlreadyLoged():Boolean{
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