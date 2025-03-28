package de.slotmyvelo.auth.infrastructure.security

import de.slotmyvelo.auth.application.port.out.TokenProvider
import de.slotmyvelo.auth.domain.model.AuthUser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${spring.security.oauth2.resourceserver.jwt.secret-key}")
    secret: String,

    @Value("\${security.jwt.expiration-ms:3600000}")
    private val expirationMs: Long
) : TokenProvider {

    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    override fun generateToken(user: AuthUser): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationMs)

        return Jwts.builder()
            .setSubject(user.id.toString())
            .claim("email", user.email)
            .claim("role", user.role.name)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
}
