package de.slotmyvelo.user.infrastructure.persistence

import de.slotmyvelo.user.application.port.out.UserProfilePort
import de.slotmyvelo.user.domain.model.UserProfile
import de.slotmyvelo.user.infrastructure.persistence.entity.UserEntity
import de.slotmyvelo.user.infrastructure.persistence.entity.toDomain
import de.slotmyvelo.user.infrastructure.persistence.entity.toEntity
import de.slotmyvelo.user.infrastructure.persistence.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserProfilePersistenceAdapter(
    private val userRepository: UserRepository
) : UserProfilePort {

    override fun loadById(userId: Long): UserProfile? {
        return userRepository.findById(userId).orElse(null)?.toDomain()
    }

    override fun save(userProfile: UserProfile): UserProfile {
        val entity = userProfile.toEntity()
        val saved = userRepository.save(entity)
        return saved.toDomain()
    }
}
