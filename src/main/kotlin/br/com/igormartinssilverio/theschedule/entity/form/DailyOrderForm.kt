package br.com.igormartinssilverio.theschedule.entity.form

import br.com.igormartinssilverio.theschedule.entity.enum.CategoryEnum
import java.time.LocalDate
import javax.validation.constraints.NotEmpty

data class DailyOrderForm (
    @field:NotEmpty(message = "Description should not be empty!")
    val name: String,
    @field:NotEmpty(message = "Category should not be empty!")
    val category: CategoryEnum,
    @field:NotEmpty(message = "Period should not be empty!")
    val month: LocalDate,
//    @field:NotEmpty(message = "Username should not be empty!")
//    val username: String
)