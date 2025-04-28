package de.slotmyvelo.auth.infrastructure.persistence.repository

import de.slotmyvelo.auth.infrastructure.persistence.entity.PasswordResetTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.UUID

interface PasswordResetTokenRepository : JpaRepository<PasswordResetTokenEntity, UUID> {

    @Modifying
    @Query("DELETE FROM PasswordResetTokenEntity e WHERE e.expiresAt < :now")
    fun deleteAllExpiredSince(@Param("now") now: LocalDateTime)
}
