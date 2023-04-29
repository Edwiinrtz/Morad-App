package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edwiinrtz.morad_app.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JoinMorada(closeAction:()->Unit, join:()->Unit,changeValue:(String)->Unit, text:String) {


    val swipeableState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd) {
                closeAction()
            }
            true
        }
    )
    SwipeToDismiss(
        state = swipeableState,
        background = { Box(modifier = Modifier.fillMaxWidth()) },
        directions = setOf(DismissDirection.StartToEnd)

    ) {
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth(1F),
            elevation = 10.dp
        ) {
            Column(
                Modifier
                    .padding(horizontal = 20.dp, vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "POR FAVOR INGRESE EL ID DE LA MORADA",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 20.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Black,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
                Input(label = "ID", text) {changeValue(it)}
                Spacer(modifier = Modifier.height(5.dp))
                Button(text = "Unirme") {join()}
            }
        }
    }

}

@Preview
@Composable
fun JoinMoradaPreview() {
    JoinMorada({},{},{},"")
}