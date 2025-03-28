package de.slotmyvelo.auth.web.dto

data class LoginRequest(
    val email: String,
    val password: String
)
