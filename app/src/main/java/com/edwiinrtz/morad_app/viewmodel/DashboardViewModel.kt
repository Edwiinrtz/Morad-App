package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwiinrtz.morad_app.model.Morada
import com.edwiinrtz.morad_app.model.Persona
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class DashboardViewModel(val auth: FirebaseAuth): ViewModel() {

    var database = Firebase.database.reference

    private val _morada = MutableLiveData<Morada>()
    var morada: LiveData<Morada> = _morada

    private val _home = MutableLiveData<Boolean>()
    var home: LiveData<Boolean> = _home

    private val _bottomView = MutableLiveData<String>()
    var bottomView: LiveData<String> = _bottomView

    private val _drawer = MutableLiveData<String>()
    var drawer: LiveData<String> = _drawer

    fun createMorada(creatorUser: FirebaseUser){
        val nMorada = Morada(notesActive = emptyList(), notesArchived = emptyList(), members = emptyList(), ssid = "Bankai")

        database.child("users").child(creatorUser.uid).get().addOnCompleteListener {task ->
            val result = task.result.value.toString()
            val user = Gson().fromJson(result, Persona::class.java)
            val listUser = listOf(user)
            nMorada.members = listUser
            database.child("moradas").child(nMorada.id?:"").setValue(nMorada).addOnCompleteListener{
                _morada.value = nMorada
                database.child("users").child(creatorUser.uid).child("morada_id").setValue(morada.value?.id)

            }
        }
    }

    fun getMorada(){
        database.child("users").child(auth.currentUser?.uid!!).get().addOnCompleteListener { task ->
            val result = task.result.value.toString()
            val user = Gson().fromJson(result, Persona::class.java)
            val morada_id = user.morada_id!!

            database.child("moradas").child(morada_id).get().addOnCompleteListener {
                val returned_morada = Gson().fromJson(it.result.value.toString(), Morada::class.java)
                _morada.value = returned_morada
                _home.value = true
                _bottomView.value = "addbutton"
                Log.i("getMorada", _morada.value.toString())
            }
        }
    }

    fun changeBottonView(nView:String){
        _bottomView.value = nView
    }

    fun openDrawer(nDrawer:String){
        _drawer.value = nDrawer
    }

}