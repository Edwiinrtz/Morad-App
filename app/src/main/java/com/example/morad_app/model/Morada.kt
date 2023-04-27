package com.example.morad_app.model

data class Morada(
    val id: String,
    val notesActive: List<Note>,
    val notesArchived: List<Note>,
    val members: List<Persona>,
    val ssid: String
)
