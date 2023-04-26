package com.example.morad_app.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.morad_app.view.ui.theme.InputBackground
import com.example.morad_app.view.ui.theme.Teal200

@Composable
fun Input(label: String) {
    var text by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    TextField(value = text, singleLine = true, onValueChange = {

        if (label == "Contraseña") {
            text = "*".repeat(it.length)
            pass = it
        }else{
            text = it
        }

    },
        modifier = Modifier
        .fillMaxWidth(.8f)
        .padding(0.dp, 10.dp)
        .background(color = InputBackground, shape = RoundedCornerShape(10.dp)),
        label = { Text(text = label, color = Color(20, 20, 20, 128)) }
    )

}

@Preview(showBackground = true)
@Composable
fun InputPreview() {
    Input(label = "Email")

}


