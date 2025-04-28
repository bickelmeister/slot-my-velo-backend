package de.slotmyvelo.auth.infrastructure.persistence.entity

import de.slotmyvelo.auth.domain.model.UserRole
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class AuthUserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var passwordHash: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    val role: UserRole,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(
        id = null,
        name = "",
        email = "",
        passwordHash = "",
        role = UserRole.CUSTOMER,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
}
