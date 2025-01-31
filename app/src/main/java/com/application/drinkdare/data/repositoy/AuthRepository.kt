package com.application.drinkdare.data.repositoy

import com.application.drinkdare.domain.model.User


class AuthRepository {
    private val users = listOf(
        User("user@example.com", "password123")
    )

    fun login(email: String, password: String): Boolean {
        return users.any { it.email == email && it.password == password }
    }
}
