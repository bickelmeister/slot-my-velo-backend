package de.slotmyvelo.auth.application.service

import de.slotmyvelo.auth.application.exception.InvalidCredentialsException
import de.slotmyvelo.auth.application.exception.InvalidTokenException
import de.slotmyvelo.auth.application.port.`in`.EmailUseCase
import de.slotmyvelo.auth.application.port.`in`.LoginCommand
import de.slotmyvelo.auth.application.port.`in`.LoginUseCase
import de.slotmyvelo.auth.application.port.out.AuthUserPersistencePort
import de.slotmyvelo.auth.application.port.out.TokenProvider
import de.slotmyvelo.auth.web.dto.LoginResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.math.log

@Service
class LoginService(
    private val authUserPersistencePort: AuthUserPersistencePort,
    private val passwordHashingService: PasswordHashingService,
    private val tokenProvider: TokenProvider,
    private val emailUseCase: EmailUseCase,
    @Value("\${app.frontend.base-url}")
    private val frontendBaseUrl: String,
) : LoginUseCase {

    override fun login(command: LoginCommand): LoginResponse {
        val user = authUserPersistencePort.findByEmail(command.email)
            ?: throw InvalidCredentialsException()

        if (!passwordHashingService.verify(command.password, user.passwordHash)) {
            throw InvalidCredentialsException()
        }

        val token = tokenProvider.generateToken(user)
        return LoginResponse(accessToken = token)
    }

    override fun initiatePasswordReset(email: String) {
        val user = authUserPersistencePort.findByEmail(email)
            ?: return // keine Angabe, ob E-Mail existiert (Datenschutz)

        val token = UUID.randomUUID()
        val expiresAt = LocalDateTime.now().plusHours(1)

        tokenProvider.savePasswordResetToken(user.id!!, token, expiresAt)

        val resetLink = "${frontendBaseUrl}/reset-password?token=$token"
        //val resetLink = "http://localhost:5173/reset-password?token=$token"

        val mailBody = """
            Hallo ${user.name},

            du hast eine Anfrage zum Zurücksetzen deines Passworts gestellt.
            Bitte nutze diesen Link (gültig für 1 Stunde):

            $resetLink

            Falls du die Anfrage nicht selbst gestellt hast, kannst du diese Mail ignorieren.
            Dein slotmyvelo-Team
        """.trimIndent()

        emailUseCase.send(
            to = user.email,
            subject = "Passwort zurücksetzen",
            body = mailBody
        )
    }

    override fun resetPassword(token: UUID, newPassword: String) {
        val userId = tokenProvider.validatePasswordResetToken(token)
            ?: throw InvalidTokenException()

        val hashed = passwordHashingService.hash(newPassword)
        authUserPersistencePort.updatePassword(userId, hashed)

        tokenProvider.invalidatePasswordResetToken(token)
    }

    override fun changePassword(command: LoginCommand, oldPassword: String, newPassword: String) {
        val user = authUserPersistencePort.findByEmail(command.email)
            ?: throw InvalidCredentialsException()

        if (!passwordHashingService.verify(command.password, user.passwordHash)) {
            throw InvalidCredentialsException()
        }

        val hashed = passwordHashingService.hash(newPassword)
        authUserPersistencePort.updatePassword(user.id!!, hashed)
    }

    override fun changeEmail(userId: Long, newEmail: String) {
        authUserPersistencePort.updateEmail(userId, newEmail)
    }
}
