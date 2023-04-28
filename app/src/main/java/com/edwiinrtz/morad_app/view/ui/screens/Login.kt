package com.edwiinrtz.morad_app.view.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.edwiinrtz.morad_app.view.ui.components.Button
import com.edwiinrtz.morad_app.view.ui.components.Input
import com.edwiinrtz.morad_app.R


@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp),
                horizontalArrangement = Arrangement.Center,

            ) {
                Text(text = "¿Aun no tienes cuenta? ", color = Color(0xB3040404))
                Text(
                    text = "Registráte",
                    color = Color(0xFFEC1F1F),
                    modifier = Modifier.clickable { navController.navigate("signin") })

            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.moradapp),
                contentDescription = "Logo Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 50.dp, 0.dp, 100.dp)
            )
            Input("Email",""){}
            Input("Contraseña",""){}
            Button(text = "Ingresar",navController, "dashboard"){}
        }

    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}