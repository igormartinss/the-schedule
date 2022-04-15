package br.com.igormartinssilverio.theschedule.repository

import br.com.igormartinssilverio.theschedule.entity.DailyOrderDone
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface DailyOrderDoneRepository : JpaRepository<DailyOrderDone, Long> {
    fun findByDateBetween(startDate: LocalDate, endDate: LocalDate) : List<DailyOrderDone>?
    fun findByDailyOrderIdAndAndDate(id: Long, date: LocalDate) : DailyOrderDone?
}