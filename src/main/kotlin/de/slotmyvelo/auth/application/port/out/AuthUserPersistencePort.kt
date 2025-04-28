package de.slotmyvelo.auth.application.port.out

import de.slotmyvelo.auth.domain.model.AuthUser

interface AuthUserPersistencePort {
    fun save(user: AuthUser): AuthUser
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): AuthUser?
    fun updatePassword(userId: Long, newPasswordHash: String)
    fun updateEmail(userId: Long, newEmail: String)
}
