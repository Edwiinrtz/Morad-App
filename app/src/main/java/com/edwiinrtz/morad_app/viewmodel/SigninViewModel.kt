package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwiinrtz.morad_app.model.Persona
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SigninViewModel(val auth: FirebaseAuth) : ViewModel() {

    var database = Firebase.database.reference

    private var _name = MutableLiveData<String>()
    var name: LiveData<String> = _name

    private var _lastName = MutableLiveData<String>()
    var lastName: LiveData<String> = _lastName

    private var _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    private var _pass = MutableLiveData<String>()
    var pass: LiveData<String> = _pass

    fun onValueChange(
        nameRef: Boolean = false,
        lastNameRef: Boolean = false,
        emailRef: Boolean = false,
        passRef: Boolean = false,
        nValue: String
    ) {
        if (nameRef) _name.value = nValue
        if (lastNameRef) _lastName.value = nValue
        if (emailRef) _email.value = nValue
        if (passRef) _pass.value = nValue
    }

    fun signin(persona: Persona): Boolean {
        val mail: String = persona.Email!!
        val nPass: String = persona.pass!!
        createUser(mail, nPass, persona)

        /*if(!uid.isNullOrEmpty()){
            persona.id = uid
            if (result.isCanceled) return false
        }else{
            return false
        }*/
        //auth.createUserWithEmailAndPassword(persona.Email!!, persona.pass)
        return true

    }

    fun createUser(mail: String, pass: String, persona: Persona) {

        auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener { it ->
            if (it.isSuccessful) {
                database.child("users").child(it.result.user?.uid!!).setValue(persona)
            }
        }


    }

}