package de.slotmyvelo.auth.web.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: String
)
