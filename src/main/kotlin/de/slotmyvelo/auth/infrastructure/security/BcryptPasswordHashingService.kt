package de.slotmyvelo.auth.infrastructure.security

import de.slotmyvelo.auth.application.service.PasswordHashingService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BcryptPasswordHashingService : PasswordHashingService {

    private val encoder = BCryptPasswordEncoder()

    override fun hash(password: String): String {
        return encoder.encode(password)
    }

    override fun verify(password: String, hash: String): Boolean {
        return encoder.matches(password, hash)
    }
}
