package br.com.igormartinssilverio.theschedule.controller

import br.com.igormartinssilverio.theschedule.entity.DailyOrderDone
import br.com.igormartinssilverio.theschedule.service.DailyOrderDoneService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/v1/daily-orders-done/")
class DailyOrderDoneController(
    private val dailyOrderDoneService: DailyOrderDoneService
) {

    @GetMapping
    fun findAll() : ResponseEntity<List<DailyOrderDone>> {
        return ResponseEntity.ok(dailyOrderDoneService.findAll())
    }

    @GetMapping("/month")
    fun findByMonth(
        @RequestParam year: Int,
        @RequestParam month: Int
    ) : ResponseEntity<List<DailyOrderDone>> {
        return ResponseEntity.ok(dailyOrderDoneService.findByMonth(year, month))
    }

    @PutMapping("/{id}")
    fun markAsDone(
        @PathVariable(name = "id") id: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,

        ) : ResponseEntity<DailyOrderDone> {
        return ResponseEntity.ok(dailyOrderDoneService.markAsDone(id, date))
    }
}