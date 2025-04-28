package de.slotmyvelo.auth.web.dto

data class ChangePasswordRequest(
    val email: String,
    val oldPassword: String,
    val newPassword: String
)