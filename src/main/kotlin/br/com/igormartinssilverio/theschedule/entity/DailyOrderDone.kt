package br.com.igormartinssilverio.theschedule.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "daily_order_done")
data class DailyOrderDone (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val dailyOrder: DailyOrder? = null,
    val date: LocalDate = LocalDate.now(),
    val isDone: Boolean = false
)
