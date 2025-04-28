package de.slotmyvelo.auth.infrastructure.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "password_reset_tokens")
class PasswordResetTokenEntity(

    @Id
    @Column(name = "token", columnDefinition = "uuid")
    var token: UUID,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "expires_at", nullable = false)
    val expiresAt: LocalDateTime,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(UUID.randomUUID(), 0L, LocalDateTime.now(), LocalDateTime.now())
}