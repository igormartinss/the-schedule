package br.com.igormartinssilverio.theschedule.controller

import br.com.igormartinssilverio.theschedule.entity.DailyOrder
import br.com.igormartinssilverio.theschedule.entity.form.DailyOrderForm
import br.com.igormartinssilverio.theschedule.entity.view.DailyOrderMonthView
import br.com.igormartinssilverio.theschedule.service.DailyOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/daily-orders/")
class DailyOrderController(
    private val dailyOrderService: DailyOrderService
) {

    @GetMapping
    fun findAll() : ResponseEntity<List<DailyOrder>> {
        return ResponseEntity.ok(dailyOrderService.findAll())
    }

    @GetMapping("/month")
    fun findByMonth(
        @RequestParam year: Int,
        @RequestParam month: Int
    ) : ResponseEntity<List<DailyOrderMonthView>> {
        return ResponseEntity.ok(dailyOrderService.findByMonth(year, month))
    }

    @PostMapping
    fun createDailyOrder(@RequestBody dailyOrder : DailyOrderForm) : ResponseEntity<DailyOrder>{
        return ResponseEntity.ok(dailyOrderService.create(dailyOrder))
    }
}