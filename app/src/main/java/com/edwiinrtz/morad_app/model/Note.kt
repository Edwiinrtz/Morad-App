package com.edwiinrtz.morad_app.model

data class Note(
    val title: String,
    val description:String,
    var archived: Boolean = false
)