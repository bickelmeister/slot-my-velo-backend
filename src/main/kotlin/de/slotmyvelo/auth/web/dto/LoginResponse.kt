package de.slotmyvelo.auth.web.dto

data class LoginResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)
