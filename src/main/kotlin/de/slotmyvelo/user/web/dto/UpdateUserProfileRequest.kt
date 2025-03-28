package de.slotmyvelo.user.web.dto

data class UpdateUserProfileRequest(
    val name: String,
    val phoneNumber: String?,
    val street: String?,
    val city: String?,
    val postalCode: String?,
    val country: String?
)
