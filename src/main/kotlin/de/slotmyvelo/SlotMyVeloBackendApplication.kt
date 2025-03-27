package de.slotmyvelo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlotMyVeloBackendApplication

fun main(args: Array<String>) {
    runApplication<SlotMyVeloBackendApplication>(*args)
}
