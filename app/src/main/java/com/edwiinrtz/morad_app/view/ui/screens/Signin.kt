package com.edwiinrtz.morad_app.view.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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
import com.edwiinrtz.morad_app.model.Persona
import com.edwiinrtz.morad_app.viewmodel.LoginViewModel
import com.edwiinrtz.morad_app.viewmodel.SigninViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SigninScreen(navController: NavController, viewModel: SigninViewModel, loginViewModel: LoginViewModel) {

    val name: String by viewModel.name.observeAsState(initial = "")
    val lastName: String by viewModel.lastName.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val pass: String by viewModel.pass.observeAsState(initial = "")

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 25.dp, 0.dp, 100.dp)
            )
            Input("Nombre", name) { viewModel.onValueChange(nameRef = true, nValue = it) }
            Input("Apellido", lastName) { viewModel.onValueChange(lastNameRef = true, nValue = it) }
            Input("Email", email) { viewModel.onValueChange(emailRef = true, nValue = it) }
            Input("Contraseña", pass) { viewModel.onValueChange(passRef = true, nValue = it) }
            Button(text = "Registrar", navController, "dashboard") {
                var result = false
                val nPersona = Persona(name=name, lastName = lastName, email = email, pass = pass)
                if(!nPersona.email.isNullOrBlank()) result = viewModel.signin(nPersona)
                if (result) {
                    //loginViewModel.login(nPersona.email!!,nPersona.pass!!)
                    navController.navigate("login")
                }
            }
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
    //SigninScreen(navController){}
}