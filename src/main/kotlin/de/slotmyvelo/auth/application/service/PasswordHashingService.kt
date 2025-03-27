package de.slotmyvelo.auth.application.service

interface PasswordHashingService {
    fun hash(password: String): String
    fun verify(password: String, hash: String): Boolean
}
