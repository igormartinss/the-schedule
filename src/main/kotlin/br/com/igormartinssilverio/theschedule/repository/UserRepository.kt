package br.com.igormartinssilverio.theschedule.repository

import br.com.igormartinssilverio.theschedule.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String) : User?
}