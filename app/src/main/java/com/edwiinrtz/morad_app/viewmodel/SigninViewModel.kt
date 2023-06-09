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
import com.google.firebase.messaging.FirebaseMessaging

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

        val mail: String = persona.email!!
        val nPass: String = persona.pass!!

        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            if (!task.isSuccessful) {
                Log.w("signin", "Fetching FCM registration token failed", task.exception)
                //return@addOnCompleteListener
            }
            val token = task.result
            auth.createUserWithEmailAndPassword(mail, nPass).addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    persona.id = it.result.user?.uid!!
                    persona.notificationId = token.toString()
                    database.child("users").child(persona.id!!).setValue(persona)
                    _email.value = ""
                    _pass.value = ""
                    _name.value = ""
                    _lastName.value = ""

                }
            }
        }
        return true

    }

}