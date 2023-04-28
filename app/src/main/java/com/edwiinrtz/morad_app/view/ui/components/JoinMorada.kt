package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
fun JoinMorada() {
    Card(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxWidth(1F)
            .fillMaxHeight(.5F)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            Modifier
                .padding(horizontal = 20.dp, vertical = 50.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "POR FAVOR CREA UNA MORADA O UNETE A UNA",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(0.dp, 20.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Black,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            Input(label = "ID",""){}
            Button(text = "Unirme"){}
        }
    }
}

@Preview
@Composable
fun JoinMoradaPreview() {
    JoinMorada()
}