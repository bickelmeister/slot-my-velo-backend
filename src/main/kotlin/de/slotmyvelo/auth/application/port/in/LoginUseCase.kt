package de.slotmyvelo.auth.application.port.`in`

import de.slotmyvelo.auth.web.dto.LoginResponse
import java.util.UUID

interface LoginUseCase {
    fun login(command: LoginCommand): LoginResponse
    fun initiatePasswordReset(email: String)
    fun resetPassword(token: UUID, newPassword: String)
    fun changePassword(command: LoginCommand, oldPassword: String, newPassword: String)
    fun changeEmail(userId: Long, newEmail: String)
}
