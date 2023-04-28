package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginViewModel(val auth: FirebaseAuth) : ViewModel() {

    var database = Firebase.database.reference

    private var _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    private var _pass = MutableLiveData<String>()
    var pass: LiveData<String> = _pass

    lateinit var user: FirebaseUser

    fun onValueChange(
        emailRef: Boolean = false,
        passRef: Boolean = false,
        nValue: String
    ) {
        if (emailRef) _email.value = nValue
        if (passRef) _pass.value = nValue
    }


    fun login(mail: String, pass: String) {

        val task = auth.signInWithEmailAndPassword(mail, pass)

        if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            Log.d("TAG", "signInWithEmail:success")
            user = auth.currentUser!!

        } else {
            // If sign in fails, display a message to the user.
            Log.w("TAG", "signInWithEmail:failure", task.exception)

            //updateUI(null)
        }
    }

}

