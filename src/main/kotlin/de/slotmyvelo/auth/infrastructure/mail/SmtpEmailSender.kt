package de.slotmyvelo.auth.infrastructure.mail

import de.slotmyvelo.auth.application.port.out.EmailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class SmtpEmailSender(
    private val mailSender: JavaMailSender
) : EmailSender {

    override fun send(to: String, subject: String, body: String) {
        val message = SimpleMailMessage().apply {
            setTo(to)
            setSubject(subject)
            setText(body)
        }
        mailSender.send(message)
    }
}