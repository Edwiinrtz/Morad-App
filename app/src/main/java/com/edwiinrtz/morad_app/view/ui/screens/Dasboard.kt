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
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.edwiinrtz.morad_app.model.Morada
import com.edwiinrtz.morad_app.model.Note
import com.edwiinrtz.morad_app.viewmodel.DashboardViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    navController: NavController,
    currentUser: FirebaseUser?,
    viewModel: DashboardViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    //val home: Boolean by viewModel.home.observeAsState(initial = false)
    val bottomView: String by viewModel.bottomView.observeAsState(initial = "")
    val drawer: String by viewModel.drawer.observeAsState(initial = "")
    val contentView: String by viewModel.content.observeAsState(initial = "nomorada")
    val joinText: String by viewModel.joinMoradaText.observeAsState(initial = "")


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
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
        drawerContent = {
            if (drawer == "profile") ProfileDrawer(
                viewModel.user.value?.name.toString(),
                viewModel.user.value?.lastName.toString(),
                currentUser?.email.toString(),

                ) {
                viewModel.signout()
                navController.navigate("login"){
                    popUpTo(navController.graph.id){
                        inclusive=true
                    }
                }
            }
            if (drawer == "morada") MoradaDrawer(morada = viewModel.morada.value ?: null)
        },
        bottomBar = {
            if (bottomView == "joinmorada") {
                JoinMorada(
                    closeAction = { viewModel.changeBottonView("") },
                    changeValue = { viewModel.onValueChange(joinRef = true, nValue = it) },
                    text = joinText,
                    join = { viewModel.joinMorada() }
                )
            }
            if (bottomView == "addbutton") addButton { viewModel.changeBottonView("addnote") }
            if (bottomView == "addnote") AddNote { viewModel.changeBottonView("addbutton") }
        },
        content = {
            if (contentView == "list") {
                NoteList(
                    viewModel.morada.value?.notesActive ?: emptyList(),
                    padding = it
                )
            }

            if (contentView == "nomorada") {
                NoMorada(
                    it = it,
                    crearAction = {
                        viewModel.createMorada(currentUser!!)
                    },
                    joinAction = { viewModel.changeBottonView("joinmorada") })
            }


        }
    )
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

