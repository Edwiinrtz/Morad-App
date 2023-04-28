package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwiinrtz.morad_app.model.Persona
import com.google.firebase.auth.FirebaseAuth
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
        try {
            val result = database.child("users").child(persona.id).setValue(persona)
            if (result.isCanceled) return false

        } catch (e: Exception) {
            Log.e("err:", e.toString())
        }
        val mail: String =persona.Email!!
        val nPass: String=persona.pass!!
        createUser(mail,nPass)
        //auth.createUserWithEmailAndPassword(persona.Email!!, persona.pass)
        return true

    }

    fun createUser(mail: String, pass: String) {
        try {
            val task = auth.createUserWithEmailAndPassword(mail, pass)
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("TAG", "createUserWithEmail:success")
                //val user = auth.currentUser
            } else {
                // If sign in fails, display a message to the user.
                Log.w("TAG", "createUserWithEmail:failure: " + task.exception)
                /*Toast.makeText(
                baseContext, "Authentication failed.", Toast.LENGTH_SHORT
            ).show()*/
            }
        }catch (e:Exception){
            Log.w("TAG", "createUserWithEmail:failure: " + e.toString())
        }

    }

}