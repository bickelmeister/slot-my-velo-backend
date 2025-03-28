package de.slotmyvelo.auth.application.port.`in`

import de.slotmyvelo.auth.web.dto.LoginResponse

interface LoginUseCase {
    fun login(command: LoginCommand): LoginResponse
}
