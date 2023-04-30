package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileDrawer(name: String, lastName: String, email: String, signout:()->Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(top = 50.dp)) {
            Text(
                text = name + " " + lastName,
                fontSize = 32.sp,
                color = Color.Black,
                fontWeight = FontWeight.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = email,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
        Button(text = "Cerrar Sesion"){signout()}

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDrawerPreview() {
    ProfileDrawer("Edwin", "Palacios", "edwin@email.com"){}
}