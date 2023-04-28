package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun addButton() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(80.dp),
            //backgroundColor = Color(0x80BABABA),

            ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun addButtonPreview() {
    addButton()
}