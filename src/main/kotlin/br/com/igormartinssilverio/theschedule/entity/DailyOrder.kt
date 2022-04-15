package br.com.igormartinssilverio.theschedule.entity

import br.com.igormartinssilverio.theschedule.entity.enum.CategoryEnum
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "daily_order")
data class DailyOrder (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String = "",
    val category: CategoryEnum = CategoryEnum.GOD,
    val month: LocalDate,
    @ManyToOne
    val user: User? = null,
    @OneToMany
    val dailyOrderDone: List<DailyOrderDone>? = null
)