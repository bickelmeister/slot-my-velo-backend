package de.slotmyvelo.auth.application.port.`in`

data class LoginCommand(
    val email: String,
    val password: String
)
