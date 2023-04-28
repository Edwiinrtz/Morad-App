package com.edwiinrtz.morad_app.model

import java.util.UUID

data class Persona(
    var id:String? = "",
    val name: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val pass: String? = null,
    val morada_id: String? = null,
    val atHome: Boolean = false
)