package de.slotmyvelo.auth.infrastructure.security

import de.slotmyvelo.auth.application.port.out.TokenProvider
import de.slotmyvelo.auth.domain.model.AuthUser
import de.slotmyvelo.auth.infrastructure.persistence.entity.PasswordResetTokenEntity
import de.slotmyvelo.auth.infrastructure.persistence.repository.PasswordResetTokenRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JwtTokenProvider(
    keyProvider: JwtKeyProvider,
        @Value("\${security.jwt.expiration-ms:86400000}")
    private val expirationMs: Long,
    private val repository: PasswordResetTokenRepository
): TokenProvider {
    private val key = keyProvider.key

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

    override fun savePasswordResetToken(userId: Long, token: UUID, expiresAt: LocalDateTime) {
        val entity = PasswordResetTokenEntity(
            token = token,
            userId = userId,
            expiresAt = expiresAt
        )
        repository.save(entity)
    }

    override fun validatePasswordResetToken(token: UUID): Long? {
        val tokenEntity = repository.findById(token).orElse(null) ?: return null
        return if (tokenEntity.expiresAt.isAfter(LocalDateTime.now())) {
            tokenEntity.userId
        } else null
    }

    override fun invalidatePasswordResetToken(token: UUID) {
        repository.deleteById(token)
    }
}
