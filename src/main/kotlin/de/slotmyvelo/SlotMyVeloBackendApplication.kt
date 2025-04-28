package de.slotmyvelo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SlotMyVeloBackendApplication

fun main(args: Array<String>) {
    runApplication<SlotMyVeloBackendApplication>(*args)
}
