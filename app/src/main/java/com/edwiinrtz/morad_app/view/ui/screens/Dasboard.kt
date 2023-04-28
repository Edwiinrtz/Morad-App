package com.edwiinrtz.morad_app.view.ui.components

import android.util.Log
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.edwiinrtz.morad_app.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun DashboardScreen(list:List<Note> = emptyList()) {
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

