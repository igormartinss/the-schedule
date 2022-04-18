package br.com.igormartinssilverio.theschedule.repository

import br.com.igormartinssilverio.theschedule.entity.DailyOrderDone
import br.com.igormartinssilverio.theschedule.entity.view.DailyOrderDoneView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyOrderDoneRepository : JpaRepository<DailyOrderDone, Long> {
    fun findByDateBetween(startDate: LocalDate, endDate: LocalDate) : List<DailyOrderDone>?
    fun findByDailyOrderIdAndAndDate(id: Long, date: LocalDate) : DailyOrderDone?
    fun findByDailyOrderIdAndAndDateBetweenOrderByDate(id: Long, startDate: LocalDate, endDate: LocalDate) : List<DailyOrderDone>
    fun findByIdAndDateBetween(
        id: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<DailyOrderDoneView>
}