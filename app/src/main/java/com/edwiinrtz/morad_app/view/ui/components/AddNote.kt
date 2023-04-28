package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddNote(action: () -> Unit) {

    val swipeableState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd) {
                action()
            }
            true
        }
    )

    SwipeToDismiss(
        state = swipeableState,
        background = {Box(modifier = Modifier.fillMaxWidth())},
        directions = setOf(DismissDirection.StartToEnd)

    ) {
        Card(
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            modifier = Modifier
                .fillMaxWidth(1F),
            elevation = 10.dp
        ) {
            Column(
                Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Input(label = "Titulo", "") {}
                Input(label = "Descripción", "") {}
                Button(text = "Enviar") {}
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun AddNotePreview() {
    AddNote() {}
}