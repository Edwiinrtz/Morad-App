package com.edwiinrtz.morad_app.model

import java.util.UUID

data class Persona(
    var id:String? = "",
    val name: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val pass: String? = null,
    var morada_id: String? = null,
    var atHome: Boolean? = false,
    var notificationId: String? = null,
)