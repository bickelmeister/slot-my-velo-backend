package de.slotmyvelo.auth.infrastructure.security

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class JwtKeyProvider(
    @Value("\${spring.security.oauth2.resourceserver.jwt.secret-key}")
    secret: String
) {
    val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())
}