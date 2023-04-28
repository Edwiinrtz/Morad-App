package com.edwiinrtz.morad_app.view.ui.components

import android.util.Log
import androidx.compose.material.DrawerState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.edwiinrtz.morad_app.model.Morada
import com.edwiinrtz.morad_app.model.Note
import com.edwiinrtz.morad_app.viewmodel.DashboardViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(list: List<Note> = emptyList(), currentUser: FirebaseUser?, viewModel: DashboardViewModel) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val mor = Morada(id = "", notesActive = emptyList(), notesArchived = emptyList(), members = emptyList(), ssid = "Bankai")

    val morada: Morada by viewModel.morada.observeAsState(mor)
    val home: Boolean by viewModel.home.observeAsState(initial = false)
    val bottomView: String by viewModel.bottomView.observeAsState(initial = "")
    val drawer :String by viewModel.drawer.observeAsState(initial = "")

    //var morada: Morada by remember { mutableStateOf( )  }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                home = home,
                profileAction = {
                    viewModel.openDrawer("profile")
                    scope.launch { scaffoldState.drawerState.open() }
                },
                moradaAction = {
                    viewModel.openDrawer("morada")
                    scope.launch { scaffoldState.drawerState.open() }
                }
            )
        },
        drawerContent ={
            if(drawer == "profile") ProfileDrawer("Edwin", "Palacios", "edwin@email.com")
            if(drawer == "morada") MoradaDrawer(morada = morada)
        },
        bottomBar = {
            if(bottomView == "joinmorada") JoinMorada(
                closeAction = { viewModel.changeBottonView("") },
                join = {}
            )
            if(bottomView == "addbutton") addButton{viewModel.changeBottonView("addnote")}
            if(bottomView == "addnote") AddNote{viewModel.changeBottonView("addbutton")}
        },



    ) {
        if(morada.id==""){
            NoMorada(
                it = it,
                crearAction = { viewModel.createMorada(currentUser!!) },
                joinAction = {viewModel.changeBottonView("joinmorada")})
        }else{
            NoteList(morada.notesActive?: emptyList(), padding = it)
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

