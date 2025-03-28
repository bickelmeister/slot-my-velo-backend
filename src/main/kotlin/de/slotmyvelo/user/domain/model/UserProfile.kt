package de.slotmyvelo.user.domain.model

import de.slotmyvelo.auth.domain.model.UserRole
import java.time.LocalDateTime

data class UserProfile(
    val id: Long,
    val name: String,
    val email: String,
    val phoneNumber: String?,
    val role: UserRole,
    val street: String?,
    val city: String?,
    val postalCode: String?,
    val country: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
