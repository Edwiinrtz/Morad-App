package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
fun TopBar(home: Boolean = false, profileAction: ()->Unit) {
    var positions = Arrangement.End

    if(home) positions = Arrangement.SpaceBetween

    TopAppBar(
        backgroundColor = Color(0x33C6D8DF),
        modifier = Modifier
            .fillMaxWidth(1f)
        ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(color = Color(0x33C6D8DF)),
            horizontalArrangement = positions
        ) {
            if(home){
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "",
                    Modifier.size(32.dp).clickable {  },
                    tint = Color(0xFFBABABA),

                )
            }
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "",
                Modifier.size(32.dp).clickable {  profileAction },
                tint = Color(0xFFBABABA)

                )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(true){}
}