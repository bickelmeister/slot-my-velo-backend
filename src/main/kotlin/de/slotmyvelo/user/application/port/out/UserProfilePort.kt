package de.slotmyvelo.user.application.port.out

import de.slotmyvelo.user.domain.model.UserProfile

interface UserProfilePort {
    fun loadById(userId: Long): UserProfile?
    fun save(userProfile: UserProfile): UserProfile
}
