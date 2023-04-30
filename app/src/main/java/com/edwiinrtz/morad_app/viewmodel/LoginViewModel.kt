package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(private val auth: FirebaseAuth) : ViewModel() {


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


    fun login(mail: String, pass: String, action:()->Unit) {

        auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("TAG", "signInWithEmail:success")
                _email.value = ""
                _pass.value = ""
                action()
                //user = auth.currentUser!!
            } else {
                // If sign in fails, display a message to the user.
                //updateUI(null)
            }
        }
    }

}

