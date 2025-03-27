package de.slotmyvelo.auth.application.service

import de.slotmyvelo.auth.application.port.`in`.RegisterCommand
import de.slotmyvelo.auth.application.port.`in`.RegisterUseCase
import de.slotmyvelo.auth.application.port.out.AuthUserPersistencePort
import de.slotmyvelo.auth.domain.model.AuthUser
import de.slotmyvelo.auth.domain.model.UserRole
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RegisterService(
    private val passwordHashingService: PasswordHashingService,
    private val authUserPersistencePort: AuthUserPersistencePort
) : RegisterUseCase {

    override fun register(command: RegisterCommand): AuthUser {
        if (authUserPersistencePort.existsByEmail(command.email)) {
            throw IllegalArgumentException("E-Mail already in use")
        }

        val hashedPassword = passwordHashingService.hash(command.password)
        val user = AuthUser(
            id = null,
            name = command.name,
            email = command.email,
            passwordHash = hashedPassword,
            role = UserRole.fromString(command.role),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        return authUserPersistencePort.save(user)
    }
}
