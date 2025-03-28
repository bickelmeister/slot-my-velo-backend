package de.slotmyvelo.auth.web.exception

data class ApiValidationError(
    val field: String,
    val message: String
)
