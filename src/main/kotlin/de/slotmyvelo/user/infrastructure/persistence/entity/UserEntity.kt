package de.slotmyvelo.user.infrastructure.persistence.entity

import de.slotmyvelo.auth.domain.model.UserRole
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(name = "phone_number")
    val phoneNumber: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    val role: UserRole,

    val street: String? = null,
    val city: String? = null,

    @Column(name = "postal_code")
    val postalCode: String? = null,

    val country: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(
        null, "", "", null, UserRole.CUSTOMER,
        null, null, null, null,
        LocalDateTime.now(), LocalDateTime.now()
    )
}
