package de.slotmyvelo.auth.application.port.`in`

import de.slotmyvelo.auth.domain.model.AuthUser

interface RegisterUseCase {
    fun register(command: RegisterCommand): AuthUser
}
