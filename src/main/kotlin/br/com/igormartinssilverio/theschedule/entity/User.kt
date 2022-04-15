package br.com.igormartinssilverio.theschedule.entity

import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String = "",
    val password: String = "",
    val email: String = ""
)
