package com.edwiinrtz.morad_app.model

import java.util.UUID

data class Persona(
    val id:String = UUID.randomUUID().toString().substring(0,15).split("-").first(),
    val name: String? = null,
    val lastName: String? = null,
    val Email: String? = null,
    val pass: String? = null,
    val morada_id: String? = null,
    val atHome: Boolean = false
)