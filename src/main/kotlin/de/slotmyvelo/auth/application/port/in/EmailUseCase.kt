package de.slotmyvelo.auth.application.port.`in`

interface EmailUseCase {
    fun send(to: String, subject: String, body: String)
}