package br.com.igormartinssilverio.theschedule.repository

import br.com.igormartinssilverio.theschedule.entity.DailyOrder
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface DailyOrderRepository : JpaRepository<DailyOrder, Long>{
    fun findByDailyOrderDoneDateBetween(start: LocalDate, end: LocalDate): List<DailyOrder>?
}