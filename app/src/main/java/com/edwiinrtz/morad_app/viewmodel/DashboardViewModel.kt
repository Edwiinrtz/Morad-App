package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwiinrtz.morad_app.model.Morada
import com.edwiinrtz.morad_app.model.Note
import com.edwiinrtz.morad_app.model.Persona
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlin.random.Random

class DashboardViewModel(val auth: FirebaseAuth) : ViewModel() {

    var database = Firebase.database.reference

    private val _morada = MutableLiveData<Morada>()
    var morada: LiveData<Morada> = _morada

    /*private val _home = MutableLiveData<Boolean>()
    var home: LiveData<Boolean> = _home*/
    private val _bottomView = MutableLiveData<String>()
    var bottomView: LiveData<String> = _bottomView

    private val _drawer = MutableLiveData<String>()
    var drawer: LiveData<String> = _drawer

    private val _content = MutableLiveData<String>()
    var content: LiveData<String> = _content

    private val _user = MutableLiveData<Persona>()
    var user: LiveData<Persona> = _user

    private val _joinMoradaText = MutableLiveData<String>()
    var joinMoradaText: LiveData<String> = _joinMoradaText

    fun onValueChange(
        joinRef: Boolean = false,
        nValue: String
    ) {
        if (joinRef) _joinMoradaText.value = nValue
    }

    fun createMorada(creatorUser: FirebaseUser) {
        val nMorada = Morada(
            id = "" + Random.nextInt(1000, 9999),
            notesActive = mutableListOf(),
            notesArchived = mutableListOf(),
            members = mutableListOf(),
            ssid = "Bankai"
        )

        database.child("users").child(creatorUser.uid).get().addOnCompleteListener { task ->
            val result = task.result.value.toString()
            val user = Gson().fromJson(result, Persona::class.java)
            val listUser = mutableListOf(user)
            nMorada.members = listUser
            database.child("moradas").child(nMorada.id ?: "").setValue(nMorada)
                .addOnCompleteListener {
                    _content.value = "list"
                    _bottomView.value = "addbutton"
                    _morada.value = nMorada
                    database.child("users").child(creatorUser.uid).child("morada_id")
                        .setValue(morada.value?.id)
                }
        }
    }

    fun joinMorada() {
        Log.i("viewModel", user.value.toString())
        if (!joinMoradaText.value.isNullOrEmpty()) {
            database.child("moradas").child(joinMoradaText.value!!).get().addOnCompleteListener {
                val tempMorada = Gson().fromJson(it.result.value.toString(), Morada::class.java)
                //val added = tempMorada.members?.add(auth.currentUser)

                //if (added == true) {
                database.child("users").child(auth.currentUser?.uid!!).get().addOnCompleteListener{ xx ->
                    val nUser = Gson().fromJson(xx.result.value.toString(), Persona::class.java)

                    tempMorada.members?.add(nUser)
                    Log.i("members", tempMorada.members.toString())

                    database.child("moradas").child(tempMorada.id ?: "").setValue(tempMorada)
                        .addOnCompleteListener {
                            _morada.value = tempMorada
                            _user.value?.morada_id = tempMorada.id.toString()
                            nUser.morada_id = tempMorada.id.toString()
                            database.child("users").child(nUser.id!!).setValue(nUser)
                        }
                    _content.value = "list"
                    _bottomView.value = "addbutton"
                    //}
                }

            }

        }
    }

    fun getMorada() {
        Log.i("ViewModel", auth.currentUser?.uid!!)
        database.child("users").child(auth.currentUser?.uid!!).get().addOnCompleteListener { task ->
            val result = task.result.value.toString()
            val user = Gson().fromJson(result, Persona::class.java)
            val morada_id = user.morada_id ?: ""

            database.child("moradas").child(morada_id).get().addOnCompleteListener {
                val returned_morada =
                    Gson().fromJson(it.result.value.toString(), Morada::class.java)
                _morada.value = returned_morada
                if (_morada.value?.id != null) {
                    _bottomView.value = "addbutton"
                    _content.value = "list"
                }
                //redirectDasboard()
            }
        }
    }

    fun getUser() {
        database.child("users").child(auth.currentUser?.uid!!).get().addOnCompleteListener { task ->
            val result = task.result.value.toString()
            _user.value = Gson().fromJson(result, Persona::class.java)

        }
    }

    fun changeBottonView(nView: String) {
        _bottomView.value = nView
    }

    fun changeContentState(nView: String) {
        _content.value = nView
    }

    fun openDrawer(nDrawer: String) {
        _drawer.value = nDrawer
    }


}