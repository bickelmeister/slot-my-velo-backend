package de.slotmyvelo.user.infrastructure.persistence

import de.slotmyvelo.user.application.port.out.LoadUserProfilePort
import de.slotmyvelo.user.domain.model.UserProfile
import de.slotmyvelo.user.infrastructure.persistence.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserProfilePersistenceAdapter(
    private val userRepository: UserRepository
) : LoadUserProfilePort {

    override fun loadById(userId: Long): UserProfile? {
        return userRepository.findById(userId).orElse(null)?.let {
            UserProfile(
                id = it.id!!,
                name = it.name,
                email = it.email,
                phoneNumber = it.phoneNumber,
                role = it.role,
                street = it.street,
                city = it.city,
                postalCode = it.postalCode,
                country = it.country,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }
}
