package com.edwiinrtz.morad_app.view.ui.components

import android.util.Log
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.edwiinrtz.morad_app.model.Morada
import com.edwiinrtz.morad_app.model.Note
import com.edwiinrtz.morad_app.viewmodel.DashboardViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun DashboardScreen(list: List<Note> = emptyList(), currentUser: FirebaseUser?, viewModel: DashboardViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val scope = rememberCoroutineScope()
    var morada: Morada by remember { mutableStateOf( Morada(id = "", notesActive = emptyList(), notesArchived = emptyList(), members = emptyList(), ssid = "Bankai"))  }
    Scaffold(
        topBar = {
            TopBar(
                home = false,
                profileAction = {

                }
            )
        },
        bottomBar = {
            //JoinMorada()
            if(morada.id!="")addButton()
            //AddNote()
        },
        drawerContent = {
            ProfileDrawer("Edwin", "Palacios", "edwin@email.com")
        }


    ) {
        if(morada.id==""){
            NoMorada(it = it, crearAction={
                morada = viewModel.createMorada(currentUser!!)
            })
        }else{
            NoteList(morada.notesActive, padding = it)
        }
    }
}

fun changeState(drawerState: DrawerState, scope: CoroutineScope) {
    Log.i("DRAWER STATE", "Trying to open drawer")

    /*if(drawerState.isOpen)  scope.launch { drawerState.close() }
    scope.launch{drawerState.open()}*/
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    //DashboardScreen()
}

