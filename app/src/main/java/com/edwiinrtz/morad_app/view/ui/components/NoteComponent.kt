package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edwiinrtz.morad_app.model.Note

@Composable
fun NoteComponent(note: Note) {
    var checked by remember { mutableStateOf(note.archived) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp, horizontal = 8.dp),


        elevation = 10.dp
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(10.dp)) {
            Column {
                Text(text = note.title, fontSize = 20.sp, fontWeight = FontWeight.Black)
                Text(text = note.description, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
            Checkbox(checked = checked, onCheckedChange = {
                checked = !checked;
                note.archived = checked
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotePreview() {
    val note = Note("Comprar", "Comprar crema dental y jabon de baño")
    NoteComponent(note)
}