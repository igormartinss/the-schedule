package br.com.igormartinssilverio.theschedule.service

import br.com.igormartinssilverio.theschedule.NotFoundException
import br.com.igormartinssilverio.theschedule.entity.DailyOrder
import br.com.igormartinssilverio.theschedule.entity.DailyOrderDone
import br.com.igormartinssilverio.theschedule.entity.enum.CategoryEnum
import br.com.igormartinssilverio.theschedule.entity.form.DailyOrderForm
import br.com.igormartinssilverio.theschedule.entity.view.DailyOrderDoneView
import br.com.igormartinssilverio.theschedule.entity.view.DailyOrderMonthView
import br.com.igormartinssilverio.theschedule.repository.DailyOrderRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth

@Service
class DailyOrderService(
    private val dailyOrderRepository: DailyOrderRepository,
    private val dailyOrderDoneService: DailyOrderDoneService,
    private val userService: UserService
) {
    fun findAll(): List<DailyOrder> {
        return dailyOrderRepository.findAll()
    }

    fun findByMonth(year: Int, month: Int): List<DailyOrderMonthView>? {
        val (startDate, endDate) = getStartAndEndDate(year, month)

        val dailyOrders : List<DailyOrder>? = dailyOrderRepository.findByMonthBetween(startDate, endDate)

        if (dailyOrders.isNullOrEmpty()) {
           throw NotFoundException("No Daily Order found for year: $year and $month")
        }

        val dailyOrdersView = mutableListOf<DailyOrderMonthView>()
        dailyOrders.map { it ->
            dailyOrdersView.add(
                DailyOrderMonthView(
                    name = it.name,
                    description = it.description + "",
                    id = it.id!!,
                    dailyOrdersDone = dailyOrderDoneService.findByMonthAndId(it.id, startDate, endDate).map { dailyOrderDoneToView(it) }
                )
            )
        }
        return dailyOrdersView
    }


    fun findByCategoryAndMonth(category: CategoryEnum, year: Int, month: Int): List<DailyOrderMonthView> {
        val (startDate, endDate) = getStartAndEndDate(year, month)

        val dailyOrders = dailyOrderRepository.findByCategory(category) ?: throw NotFoundException("No daily order for category $category")
        val dailyOrdersView = mutableListOf<DailyOrderMonthView>()

        dailyOrders.map {
                dailyOrdersView.add(
                    DailyOrderMonthView(
                        name = it.name,
                        id = it.id!!,
                        description = it.description,
                        dailyOrdersDone = dailyOrderDoneService.findByCategoryAndMonth(it.id, startDate, endDate)
                    )
                )
        }

        return dailyOrdersView
    }

    fun create(dailyOrderForm: DailyOrderForm): DailyOrder {
        val dailyOrderCreated = dailyOrderRepository.save(formToDomain(dailyOrderForm))
        val yearMonth : YearMonth = YearMonth.of(dailyOrderCreated.month.year, dailyOrderCreated.month.month)
        dailyOrderDoneService.createRecurrence(dailyOrderCreated, yearMonth)
        return dailyOrderCreated
    }

    private fun getStartAndEndDate(year: Int, month: Int) : Pair<LocalDate, LocalDate> {
        val yearMonth = YearMonth.of(year, month)
        val startDate = yearMonth.atDay(1)
        val endDate = yearMonth.atEndOfMonth()

        return startDate to endDate
    }

    private fun formToDomain(dailyOrderForm: DailyOrderForm) = DailyOrder(
        name = dailyOrderForm.name,
        month = dailyOrderForm.month,
        category = dailyOrderForm.category,
//        user = userService.findByUsername(dailyOrderForm.username)
    )

    private fun dailyOrderDoneToView(dailyOrderDone: DailyOrderDone) = DailyOrderDoneView(
        isDone = dailyOrderDone.isDone,
        date = dailyOrderDone.date,
    )
}