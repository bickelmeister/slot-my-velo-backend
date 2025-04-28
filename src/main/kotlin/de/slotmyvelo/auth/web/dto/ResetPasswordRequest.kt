package de.slotmyvelo.auth.web.dto

data class ResetPasswordRequest(
    val token: String,
    val newPassword: String
)