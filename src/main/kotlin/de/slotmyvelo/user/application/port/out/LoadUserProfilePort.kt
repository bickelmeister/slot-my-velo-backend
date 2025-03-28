package de.slotmyvelo.user.application.port.out

import de.slotmyvelo.user.domain.model.UserProfile

interface LoadUserProfilePort {
    fun loadById(userId: Long): UserProfile?
}
