package de.slotmyvelo.auth.application.port.out

import de.slotmyvelo.auth.domain.model.AuthUser
import java.time.LocalDateTime
import java.util.*

interface TokenProvider {
    fun generateToken(user: AuthUser): String
    fun savePasswordResetToken(userId: Long, token: UUID, expiresAt: LocalDateTime)
    fun validatePasswordResetToken(token: UUID): Long?
    fun invalidatePasswordResetToken(token: UUID)
}
