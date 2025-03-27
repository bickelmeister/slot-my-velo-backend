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
            .csrf { it.disable() } // optional für APIs
            .authorizeHttpRequests {
                it.requestMatchers("/auth/register").permitAll()
                it.anyRequest().authenticated()
            }
            .httpBasic { }

        return http.build()
    }
}
