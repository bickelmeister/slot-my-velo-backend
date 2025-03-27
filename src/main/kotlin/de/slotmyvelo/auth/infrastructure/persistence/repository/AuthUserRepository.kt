package de.slotmyvelo.auth.infrastructure.persistence.repository

import de.slotmyvelo.auth.infrastructure.persistence.entity.AuthUserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthUserRepository : JpaRepository<AuthUserEntity, Long> {
    fun existsByEmail(email: String): Boolean
}
