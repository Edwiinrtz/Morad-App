package com.example.morad_app.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Button(text: String, navController: NavController? = null, route:String="") {
    TextButton(
        onClick = { navController?.navigate(route) }, modifier = Modifier
            .fillMaxWidth(.8f)
            .padding(0.dp,80.dp)
            .background(
                color = Color(
                    0xFF24A7DF
                ),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Button("Ingresar")
}