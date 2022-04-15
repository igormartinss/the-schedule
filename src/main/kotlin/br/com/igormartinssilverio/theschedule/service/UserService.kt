package br.com.igormartinssilverio.theschedule.service

import br.com.igormartinssilverio.theschedule.NotFoundException
import br.com.igormartinssilverio.theschedule.entity.User
import br.com.igormartinssilverio.theschedule.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository
){
    fun findByUsername(username: String) : User{
        return userRepository.findByUsername(username) ?: throw NotFoundException("User not found for username: $username")
    }
}
