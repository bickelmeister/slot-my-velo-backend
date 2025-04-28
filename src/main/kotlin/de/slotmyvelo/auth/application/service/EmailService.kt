package de.slotmyvelo.auth.application.service

import de.slotmyvelo.auth.application.port.`in`.EmailUseCase
import de.slotmyvelo.auth.application.port.out.EmailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val emailSender: EmailSender
) : EmailUseCase {

    override fun send(to: String, subject: String, body: String) {
        emailSender.send(to, subject, body)
    }
}