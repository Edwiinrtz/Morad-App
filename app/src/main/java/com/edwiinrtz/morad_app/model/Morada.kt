package com.edwiinrtz.morad_app.model

import kotlin.random.Random

data class Morada(
    val id: String?= ""+Random.nextInt(1000, 9999),
    val notesActive: List<Note>,
    val notesArchived: List<Note>,
    var members: List<Persona>,
    val ssid: String
)
