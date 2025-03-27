package de.slotmyvelo.auth.web.dto

data class AuthUserResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val role: String
)
