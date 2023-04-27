package com.example.morad_app.view.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.morad_app.R
import com.example.morad_app.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun DashboardScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = { TopBar(true,profileAction = { changeState(drawerState,scope) }) },
        bottomBar = {
            //JoinMorada()
            addButton()
            //AddNote()
        },
        drawerContent = {
            //ProfileDrawer("Edwin", "Palacios", "edwin@email.com")
        }


    ) {
        //NoMorada(it = it)
        val list = listOf(
            Note("Comprar", "Comprar crema dental y jabon de baño"),
            Note("Basura", "Sacar toda la basura")

            )
        NoteList(list,padding = it)
    }
}

fun changeState(drawerState: DrawerState,scope: CoroutineScope) {
    Log.i("DRAWER STATE", "Trying to open drawer")
    if(drawerState.isOpen)  scope.launch { drawerState.close() }
    scope.launch{drawerState.open()}
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen()
}

