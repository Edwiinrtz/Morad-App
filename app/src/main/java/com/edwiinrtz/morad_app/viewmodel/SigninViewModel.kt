package com.edwiinrtz.morad_app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.edwiinrtz.morad_app.model.Persona
import com.google.android.gms.tasks.Task
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//myRef.setValue("Hello, World!")

class SigninViewModel: ViewModel() {

    var database = Firebase.database.reference

    private var _name = MutableLiveData<String>()
    var name: LiveData<String> = _name

    private var _lastName = MutableLiveData<String>()
    var lastName: LiveData<String> = _lastName

    private var _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    private var _pass = MutableLiveData<String>()
    var pass: LiveData<String> = _pass

    fun onValueChange(nameRef:Boolean=false,lastNameRef:Boolean=false,emailRef:Boolean=false,passRef:Boolean=false, nValue:String){
        if(nameRef) _name.value = nValue
        if(lastNameRef) _lastName.value = nValue
        if(emailRef) _email.value = nValue
        if(passRef) _pass.value = nValue
    }

    fun signin(persona: Persona):Boolean{
        try {
            val result =  database.child("users").child(persona.id).setValue(persona)
            if(result.isCanceled) return false

        }catch (e: Exception){
            Log.e("err:", e.toString())
        }
        return true

    }

}