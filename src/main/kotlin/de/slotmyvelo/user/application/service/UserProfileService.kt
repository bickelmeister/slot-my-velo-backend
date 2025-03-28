package de.slotmyvelo.user.application.service

import de.slotmyvelo.user.application.port.`in`.UserProfileUseCase
import de.slotmyvelo.user.application.port.out.UserProfilePort
import de.slotmyvelo.user.domain.model.UserProfile
import de.slotmyvelo.user.web.dto.UpdateUserProfileRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserProfileService(
    private val userProfilePort: UserProfilePort
) : UserProfileUseCase {

    override fun getProfile(userId: Long): UserProfile {
        return userProfilePort.loadById(userId)
            ?: throw NoSuchElementException("User with id $userId not found")
    }

    @Transactional
    override fun updateProfile(userId: Long, request: UpdateUserProfileRequest): UserProfile {
        val user = userProfilePort.loadById(userId)
            ?: throw IllegalArgumentException("User not found")

        val updatedUser = user.copy(
            name = request.name ?: user.name,
            phoneNumber = request.phoneNumber ?: user.phoneNumber,
            street = request.street ?: user.street,
            city = request.city ?: user.city,
            postalCode = request.postalCode ?: user.postalCode,
            country = request.country ?: user.country
        )

        userProfilePort.save(updatedUser)
        return updatedUser
    }
}
