package de.slotmyvelo.auth.domain.model

import java.time.LocalDateTime

data class AuthUser(
    val id: Long?,
    val name: String,
    val email: String,
    val passwordHash: String,
    val role: UserRole,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
