package de.slotmyvelo.user.application.service

import de.slotmyvelo.user.application.port.`in`.GetUserProfileUseCase
import de.slotmyvelo.user.application.port.out.LoadUserProfilePort
import de.slotmyvelo.user.domain.model.UserProfile
import org.springframework.stereotype.Service

@Service
class UserProfileService(
    private val loadUserProfilePort: LoadUserProfilePort
) : GetUserProfileUseCase {

    override fun getProfile(userId: Long): UserProfile {
        return loadUserProfilePort.loadById(userId)
            ?: throw NoSuchElementException("User with id $userId not found")
    }
}
