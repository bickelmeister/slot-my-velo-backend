package de.slotmyvelo.user.application.port.`in`

import de.slotmyvelo.user.domain.model.UserProfile

interface GetUserProfileUseCase {
    fun getProfile(userId: Long): UserProfile
}
