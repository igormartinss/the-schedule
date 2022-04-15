package br.com.igormartinssilverio.theschedule.entity.view

import java.time.LocalDate

data class DailyOrderDoneView (
    val isDone: Boolean,
    val date: LocalDate
)