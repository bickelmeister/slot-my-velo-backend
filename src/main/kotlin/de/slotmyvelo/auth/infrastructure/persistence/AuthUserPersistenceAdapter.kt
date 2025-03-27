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
}
