package com.example.morad_app.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.morad_app.view.ui.theme.InputBackground

@Composable
fun AddNote() {

    Card(
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        modifier = Modifier
            .fillMaxWidth(1F)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            Modifier
                .padding(horizontal = 20.dp, vertical = 50.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Input(label = "Titulo")
            Input(label = "Descripción")
            Button(text = "Enviar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNotePreview() {
    AddNote()
}