package br.com.igormartinssilverio.theschedule.entity.view

data class DailyOrderMonthView(
    val id: Long,
    val name: String,
    val description: String,
    val dailyOrdersDone: List<DailyOrderDoneView>
)