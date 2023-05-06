package com.edwiinrtz.morad_app.model

import java.io.Serializable

data class Note(
    val title: String?=null,
    val description:String?=null,
    var archived: Boolean = false
):Serializable