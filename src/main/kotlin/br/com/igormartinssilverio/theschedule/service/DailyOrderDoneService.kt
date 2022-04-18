package br.com.igormartinssilverio.theschedule.service

import br.com.igormartinssilverio.theschedule.NotFoundException
import br.com.igormartinssilverio.theschedule.entity.DailyOrder
import br.com.igormartinssilverio.theschedule.entity.DailyOrderDone
import br.com.igormartinssilverio.theschedule.entity.view.DailyOrderDoneView
import br.com.igormartinssilverio.theschedule.repository.DailyOrderDoneRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth

@Service
class DailyOrderDoneService (
    val dailyOrderDoneRepository: DailyOrderDoneRepository
){
    fun createRecurrence(dailyOrder: DailyOrder, yearMonth: YearMonth) : List<DailyOrderDone>{
        val startDate = yearMonth.atDay(1)
        val endDate = yearMonth.atEndOfMonth()
        val dailyOrderDoneRecurrence : MutableList<DailyOrderDone> = mutableListOf()

        var dateToAdd = startDate
        while (dateToAdd != endDate) {
           dailyOrderDoneRecurrence.add(
               DailyOrderDone(
                   dailyOrder = dailyOrder,
                   isDone = false,
                   date = dateToAdd
               )
           )
            dateToAdd = dateToAdd.plusDays(1)
        }
        return dailyOrderDoneRepository.saveAll(dailyOrderDoneRecurrence)
    }

    fun findAll(): List<DailyOrderDone>? {
        return dailyOrderDoneRepository.findAll()
    }

    fun findByMonth(year: Int, month: Int) : List<DailyOrderDone> {
        val yearMonth = YearMonth.of(year, month)
        val startDate = yearMonth.atDay(1)
        val endDate = yearMonth.atEndOfMonth()

        return dailyOrderDoneRepository.findByDateBetween(startDate, endDate) ?: throw NotFoundException("F")
    }

    fun findByMonthAndId(id: Long, startDate: LocalDate, endDate: LocalDate): List<DailyOrderDone> {
        return dailyOrderDoneRepository.findByDailyOrderIdAndAndDateBetweenOrderByDate(id, startDate, endDate)
    }

    fun markAsDone(id: Long, date: LocalDate): DailyOrderDone {
        val dailyOrderDoneToMarkAsDone = dailyOrderDoneRepository.findByDailyOrderIdAndAndDate(id, date)
            ?: throw NotFoundException("Daily Order not found for these id $id and date $date")

        val dailyOrderDoneMarkedAsDone = dailyOrderDoneToMarkAsDone.copy(isDone = true)

        return dailyOrderDoneRepository.save(dailyOrderDoneMarkedAsDone)
    }

    fun findByCategoryAndMonth(
        id: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<DailyOrderDoneView> {
        return dailyOrderDoneRepository.findByIdAndDateBetween(id, startDate, endDate)
    }
}