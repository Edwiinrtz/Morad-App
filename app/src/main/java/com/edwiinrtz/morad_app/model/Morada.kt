package com.edwiinrtz.morad_app.model

import kotlin.random.Random

data class Morada(
    val id: String?= null,
    var notesActive: MutableList<Note>?= mutableListOf(),
    var notesArchived: MutableList<Note>?= mutableListOf(),
    var members: MutableList<Persona>?= mutableListOf(),
    var ssid: String?=null
)

