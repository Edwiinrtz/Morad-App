package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.edwiinrtz.morad_app.model.Morada
import com.edwiinrtz.morad_app.model.Persona
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DashboardViewModel() : ViewModel() {

    var database = Firebase.database.reference

    fun createMorada(creatorUser: FirebaseUser): Morada{
        //val user = Persona(Email = creatorUser.email, pass =  creatorUser.)
        val nMorada = Morada(notesActive = emptyList(), notesArchived = emptyList(), members = emptyList(), ssid = "Bankai")
        try {
            val result = database.child("moradas").child(nMorada.id?:"").setValue(nMorada)
            if (result.isCanceled) return Morada(id = "", notesActive = emptyList(), notesArchived = emptyList(), members = emptyList(), ssid = "Bankai")
        } catch (e: Exception) {
            Log.e("err:", e.toString())
        }
        //auth.createUserWithEmailAndPassword(persona.Email!!, persona.pass)
        return nMorada

    }

}