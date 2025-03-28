package de.slotmyvelo.auth.application.service

import de.slotmyvelo.auth.application.port.`in`.LoginCommand
import de.slotmyvelo.auth.application.port.`in`.LoginUseCase
import de.slotmyvelo.auth.application.port.out.AuthUserPersistencePort
import de.slotmyvelo.auth.application.port.out.TokenProvider
import de.slotmyvelo.auth.web.dto.LoginResponse
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val authUserPersistencePort: AuthUserPersistencePort,
    private val passwordHashingService: PasswordHashingService,
    private val tokenProvider: TokenProvider
) : LoginUseCase {

    override fun login(command: LoginCommand): LoginResponse {
        val user = authUserPersistencePort.findByEmail(command.email)
            ?: throw IllegalArgumentException("Invalid credentials")

        if (!passwordHashingService.verify(command.password, user.passwordHash)) {
            throw IllegalArgumentException("Invalid credentials")
        }

        val token = tokenProvider.generateToken(user)

        return LoginResponse(accessToken = token)
    }
}
