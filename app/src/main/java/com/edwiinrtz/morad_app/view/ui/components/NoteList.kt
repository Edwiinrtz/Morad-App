package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edwiinrtz.morad_app.model.Note


@Composable
fun NoteList(list_note: List<Note> = emptyList(), padding: PaddingValues) {
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(padding)) {

        items(list_note){ note->
            NoteComponent(note)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteListPreview() {
    val list = listOf(
        Note("Comprar", "Comprar crema dental y jabon de baño"),
        Note("Basura", "Sacar toda la basura"),

    )
    val padding = PaddingValues(100.dp)
    NoteList(list_note = list, padding)
}