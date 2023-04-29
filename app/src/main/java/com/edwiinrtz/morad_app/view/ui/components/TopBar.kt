package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(profileAction: () -> Unit, moradaAction: () -> Unit) {
    var positions = Arrangement.SpaceBetween

    //if(home) positions = Arrangement.SpaceBetween

    TopAppBar(
        backgroundColor = Color(0x1AFFFFFF),
        elevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = positions
        ) {

            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "",
                Modifier
                    .size(32.dp)
                    .clickable { moradaAction() }
                    .padding(start = 5.dp),
                tint = Color(0xFFBABABA),
            )

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "",
                Modifier
                    .size(32.dp)
                    .clickable { profileAction() }
                    .padding(end = 5.dp),
                tint = Color(0xFFBABABA)

            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar({}, {})
}