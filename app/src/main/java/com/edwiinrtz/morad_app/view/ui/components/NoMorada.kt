package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edwiinrtz.morad_app.R

@Composable
fun NoMorada(it: PaddingValues, crearAction:()-> Unit, joinAction:()->Unit) {

    Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.houseicon),
            contentDescription = "",
            Modifier.padding(0.dp,20.dp).size(128.dp),

        )
        Text(
            text = "POR FAVOR CREA UNA MORADA O UNETE A UNA",
            modifier = Modifier.fillMaxWidth(.75f).padding(0.dp,20.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Black,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp),
            horizontalArrangement = Arrangement.Center,

            ) {
            Text(
                text = "Crear ",
                color = Color(0xFF24A7DF),
                fontSize = 16.sp,
                modifier = Modifier.clickable { crearAction() }.padding(20.dp)
            )
            Text(
                text = "Unirme",
                color = Color(0xFFEC1F1F),
                fontSize = 16.sp,
                modifier = Modifier.clickable { joinAction() }.padding(20.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoMoradaPreview() {
    //NoMorada()
}