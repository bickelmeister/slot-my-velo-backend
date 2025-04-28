package de.slotmyvelo.auth.application.port.out

interface EmailSender {
    fun send(to: String, subject: String, body: String)
}