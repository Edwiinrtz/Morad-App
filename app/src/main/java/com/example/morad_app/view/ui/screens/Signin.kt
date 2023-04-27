package com.example.morad_app.view.ui.screens

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
import com.example.morad_app.view.ui.components.Button
import com.example.morad_app.view.ui.components.Input
import com.example.morad_app.R


@Composable
fun SigninScreen(navController: NavController) {
    Scaffold { padding ->
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
                modifier = Modifier.fillMaxWidth().padding(0.dp,25.dp,0.dp,100.dp)
            )
            Input("Nombre")
            Input("Apellido")
            Input("Email")
            Input("Contraseña")
            Button(text = "Registrar", navController, "dashboard")
            Text(
                text = "Ingresar",
                color = Color(0xFFEC1F1F),
                modifier = Modifier.clickable { navController.navigate("login") })

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SiginPreview() {
    val navController = rememberNavController()
    SigninScreen(navController)
}