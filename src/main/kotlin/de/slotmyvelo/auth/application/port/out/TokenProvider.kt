package de.slotmyvelo.auth.application.port.out

import de.slotmyvelo.auth.domain.model.AuthUser

interface TokenProvider {
    fun generateToken(user: AuthUser): String
}
