package br.com.igormartinssilverio.theschedule.repository

import br.com.igormartinssilverio.theschedule.entity.DailyOrder
import br.com.igormartinssilverio.theschedule.entity.enum.CategoryEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyOrderRepository : JpaRepository<DailyOrder, Long>{
    fun findByMonthBetween(start: LocalDate, end: LocalDate): List<DailyOrder>?
    fun findByCategory(category: CategoryEnum): List<DailyOrder>?
}