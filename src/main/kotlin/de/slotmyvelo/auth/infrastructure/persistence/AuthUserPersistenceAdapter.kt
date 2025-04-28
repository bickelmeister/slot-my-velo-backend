package de.slotmyvelo.auth.infrastructure.persistence

import de.slotmyvelo.auth.application.port.out.AuthUserPersistencePort
import de.slotmyvelo.auth.application.service.AuthUserMapper
import de.slotmyvelo.auth.domain.model.AuthUser
import de.slotmyvelo.auth.infrastructure.persistence.repository.AuthUserRepository
import org.springframework.stereotype.Component

@Component
class AuthUserPersistenceAdapter(
    private val repository: AuthUserRepository
) : AuthUserPersistencePort {

    override fun save(user: AuthUser): AuthUser {
        val entity = AuthUserMapper.toEntity(user)
        val saved = repository.save(entity)
        return AuthUserMapper.toDomain(saved)
    }

    override fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email)
    }

    override fun findByEmail(email: String): AuthUser? {
        return repository.findByEmail(email)
            ?.let { AuthUserMapper.toDomain(it) }
    }

    override fun updatePassword(userId: Long, newPasswordHash: String) {
        val user = repository.findById(userId).orElseThrow {
            IllegalArgumentException("User not found")
        }

        user.passwordHash = newPasswordHash
        repository.save(user)
    }

    override fun updateEmail(userId: Long, newEmail: String) {
        val user = repository.findById(userId).orElseThrow {
            IllegalArgumentException("User not found")
        }
        user.email = newEmail
        repository.save(user)
    }
}
