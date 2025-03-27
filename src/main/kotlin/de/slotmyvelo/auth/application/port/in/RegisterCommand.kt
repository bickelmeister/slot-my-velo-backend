package de.slotmyvelo.auth.application.port.`in`

data class RegisterCommand(
    val name: String,
    val email: String,
    val password: String,
    val role: String
)
