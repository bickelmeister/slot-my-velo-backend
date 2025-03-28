package de.slotmyvelo.user.application.port.`in`

import de.slotmyvelo.user.domain.model.UserProfile
import de.slotmyvelo.user.web.dto.UpdateUserProfileRequest

interface UserProfileUseCase {
    fun getProfile(userId: Long): UserProfile
    fun updateProfile(userId: Long, request: UpdateUserProfileRequest): UserProfile
}
