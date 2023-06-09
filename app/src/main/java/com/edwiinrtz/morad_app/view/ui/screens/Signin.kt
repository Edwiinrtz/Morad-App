package com.edwiinrtz.morad_app.view.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edwiinrtz.morad_app.R
import com.edwiinrtz.morad_app.model.Persona
import com.edwiinrtz.morad_app.view.ui.components.Button
import com.edwiinrtz.morad_app.view.ui.components.Input
import com.edwiinrtz.morad_app.viewmodel.SigninViewModel


@Composable
fun SigninScreen(navController: NavController, viewModel: SigninViewModel, context: Context) {

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
                var status = true

                val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$");

                if(name.isEmpty() && lastName.isEmpty()){
                    Toast.makeText(context,"por favor agrega nombre o apellido", Toast.LENGTH_LONG).show()
                    status = false
                }

                if(pass.length < 6){
                    Toast.makeText(context,"la contraseña debe ser de 6 o mas caracteres", Toast.LENGTH_LONG).show()
                    status = false
                }

                if(!emailRegex.matches(email)){
                    Toast.makeText(context,"email no cumple con los requerimientos", Toast.LENGTH_LONG).show()
                    status = false
                }

                if(status){
                    val nPersona = Persona(name=name, lastName = lastName, email = email, pass = pass)
                    if(!nPersona.email.isNullOrBlank()) result = viewModel.signin(nPersona)
                    if (result) {
                        navController.navigate("login")
                    }
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
    //val navController = rememberNavController()
    //SigninScreen(navController){}
}