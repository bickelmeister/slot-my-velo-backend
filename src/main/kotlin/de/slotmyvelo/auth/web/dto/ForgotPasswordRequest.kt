package de.slotmyvelo.auth.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class ForgotPasswordRequest(
    @field:NotBlank
    @field:Email
    val email: String
)
