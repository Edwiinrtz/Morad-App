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

    private val _titleNote = MutableLiveData<String>()
    var titleNote: LiveData<String> = _titleNote

    private val _descrNote = MutableLiveData<String>()
    var descrNote: LiveData<String> = _descrNote

    fun onValueChange(
        joinRef: Boolean = false,
        titleRef: Boolean = false,
        descRef: Boolean = false,
        nValue: String
    ) {
        if (joinRef) _joinMoradaText.value = nValue
        if (titleRef) _titleNote.value = nValue
        if (descRef) _descrNote.value = nValue
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
            val result = Gson().toJson(task.result.value)
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
                val tempMorada = Gson().fromJson(Gson().toJson(it.result.value), Morada::class.java)
                //val added = tempMorada.members?.add(auth.currentUser)

                //if (added == true) {
                database.child("users").child(auth.currentUser?.uid!!).get()
                    .addOnCompleteListener { xx ->
                        val nUser = Gson().fromJson(Gson().toJson(xx.result.value), Persona::class.java)
                        nUser.morada_id = tempMorada.id

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

    fun leaveMorada() {
        //Log.i("viewModel", user.value.toString())
        //if (!joinMoradaText.value.isNullOrEmpty()) {
        database.child("moradas").child(morada.value?.id.toString()).get().addOnCompleteListener {
            val tempMorada = Gson().fromJson(Gson().toJson(it.result.value), Morada::class.java)
            //val added = tempMorada.members?.add(auth.currentUser)

            //if (added == true) {
            database.child("users").child(auth.currentUser?.uid!!).get()
                .addOnCompleteListener { xx ->
                    val nUser = Gson().fromJson(Gson().toJson(xx.result.value), Persona::class.java)


                    Log.i("user", nUser.toString())
                    Log.i("members", tempMorada.members.toString())

                    tempMorada.members?.remove(nUser)
                    //Log.i("members", tempMorada.members.toString())
                    database.child("moradas").child(tempMorada.id ?: "").setValue(tempMorada)
                        .addOnCompleteListener {
                            //_morada.value = tempMorada
                            _user.value?.morada_id = ""
                            nUser.morada_id = ""
                            database.child("users").child(nUser.id!!).setValue(nUser)
                        }
                    _content.value = "nomorada"
                    _bottomView.value = ""
                    _drawer.value = "morada"
                    _morada.value = null
                    //}
                }
        }
        //}
    }

    fun getMorada() {
        //Log.i("ViewModel", auth.currentUser?.uid!!)
        database.child("users").child(auth.currentUser?.uid ?: "").get()
            .addOnCompleteListener { task ->
                val result = Gson().toJson(task.result.value)
                val user = Gson().fromJson(result, Persona::class.java)
                val morada_id = user.morada_id ?: ""

                database.child("moradas").child(morada_id).get().addOnCompleteListener {
                    //Log.i("viewModel", it.result.value.toString())
                    val returned_morada =
                        Gson().fromJson(Gson().toJson(it.result.value), Morada::class.java)
                    _morada.value = returned_morada
                    if (_morada.value?.id != null) {
                        _bottomView.value = "addbutton"
                        _content.value = "list"
                    }
                    //redirectDasboard()
                }
            }
    }

    fun createNota(navigate: () -> Unit) {
        if (morada.value?.id != null && titleNote.value.toString().isNotEmpty()) {
            database.child("moradas").child(morada.value?.id!!).get()
                .addOnCompleteListener {
                    //val tempMorada = it.result.value
                    //val tempJson = Gson().toJson(tempMorada)
                    val tempMorada =
                        Gson().fromJson(Gson().toJson(it.result.value), Morada::class.java)
                    val nNote = Note(
                        title = titleNote.value.toString(),
                        description = descrNote.value.toString() ?: ""
                    )
                    /*_content.value = "list"
                    _bottomView.value = "addbutton"
                    _morada.value = nMorada*/
                    tempMorada.notesActive?.add(nNote)
                    database.child("moradas").child(morada.value?.id!!).setValue(tempMorada)
                        .addOnCompleteListener {
                            navigate()
                        }
                    _titleNote.value = ""
                    _descrNote.value = ""
                    _morada.value = tempMorada

                }

        }

    }

    fun archiveNote(note: Note, action: () -> Unit) {
        //var archiveNote: Note
        val tempMorada: Morada? = morada.value

        tempMorada?.notesActive!!.map {
            if (it == note) {
                //archiveNote = note
                tempMorada.notesArchived?.add(note)
                tempMorada.notesActive?.remove(note)
                _morada.value = tempMorada
                database.child("moradas").child(tempMorada.id ?: "").setValue(tempMorada)
                    .addOnCompleteListener {
                        Log.i("viewModel", "chingon")
                        getMorada()
                        action()
                    }

            }
        }
    }


    fun getUser() {
        database.child("users").child(auth.currentUser?.uid ?: "").get()
            .addOnCompleteListener { task ->
                val result = Gson().toJson(task.result.value)
                _user.value = Gson().fromJson(result, Persona::class.java)

            }
    }

    fun signout(): Boolean {
        val result = auth.signOut()
        Log.i("viewModel", result.toString())
        return true
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
