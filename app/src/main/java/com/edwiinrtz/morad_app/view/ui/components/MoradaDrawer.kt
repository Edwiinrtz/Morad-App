package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.edwiinrtz.morad_app.model.Morada

@Composable
fun MoradaDrawer(morada: Morada? = null, abandonar:() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = morada?.id?:"",
                fontSize = 64.sp,
                color = Color.Black,
                fontWeight = FontWeight.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "id morada",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.size(60.dp))
            /*Text(
                text = "Red Wifi: " + morada?.ssid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(30.dp))*/
            Text(
                text = "Integrantes",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            LazyColumn {
                items(morada?.members?: emptyList()) {
                    Card(modifier = Modifier.padding(10.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = it.name+" "+it.lastName,
                                modifier = Modifier.fillMaxWidth(.9f),
                                textAlign = TextAlign.Start,
                                fontSize = 20.sp
                            )
                            Canvas(
                                modifier = Modifier
                                    .size(20.dp)
                            ) {
                                drawCircle(

                                    color = if (it.atHome==true) Color(0xFF00FF19) else Color(
                                        0xFFBABABA
                                    )
                                )
                            }

                        }
                    }
                }
            }
        }
        Button(text = "Abandonar Morada"){abandonar()}

    }
}

@Preview(showBackground = true)
@Composable
fun MoradaDrawerPreview() {
    //val personas = listOf(Persona("Edwin", true), Persona("Roy", false), Persona("Mariana", false))
    val morada = Morada(id = "2508",  mutableListOf(),  mutableListOf(),  mutableListOf(), "Bankai")
    MoradaDrawer(morada){}
}
