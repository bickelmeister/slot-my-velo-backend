package de.slotmyvelo.auth.infrastructure.security

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder

@Configuration
class JwtConfig {

    @Bean
    fun jwtDecoder(
        @Value("\${spring.security.oauth2.resourceserver.jwt.secret-key}")
        secret: String
    ): JwtDecoder {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        return NimbusJwtDecoder.withSecretKey(key).build()
    }
}
