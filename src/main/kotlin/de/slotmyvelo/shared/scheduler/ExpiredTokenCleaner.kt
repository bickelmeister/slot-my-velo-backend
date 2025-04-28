package de.slotmyvelo.shared.scheduler

import de.slotmyvelo.auth.infrastructure.persistence.repository.PasswordResetTokenRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

private val logger = LoggerFactory.getLogger(ExpiredTokenCleaner::class.java)

@Component
class ExpiredTokenCleaner(
    private val repository: PasswordResetTokenRepository
) {

    @Scheduled(fixedRateString = "\${cleanup.tokens.fixed-rate-ms}")
    @Transactional
    fun cleanup() {
        val deletedBefore = LocalDateTime.now()
        val deletedCount = repository.deleteAllExpiredSince(deletedBefore)
        logger.info("Deleted $deletedCount expired password reset tokens at $deletedBefore")
    }
}