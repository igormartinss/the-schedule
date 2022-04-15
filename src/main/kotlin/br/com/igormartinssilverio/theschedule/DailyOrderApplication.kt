package br.com.igormartinssilverio.theschedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DailyOrderApplication

fun main(args: Array<String>) {
    runApplication<DailyOrderApplication>(*args)
}
