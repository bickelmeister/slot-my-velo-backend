package de.slotmyvelo.auth.infrastructure.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/auth/register", "/auth/login").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2ResourceServer {
                it.jwt {  }
            }

        return http.build()
    }
}
