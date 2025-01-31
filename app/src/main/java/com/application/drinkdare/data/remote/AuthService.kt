package com.application.drinkdare.data.remote

import com.application.drinkdare.data.repositoy.AuthRepository

class AuthService(private val loginRepository: AuthRepository) {
    fun authenticate(email: String, password: String): Boolean {
        return loginRepository.login(email, password)
    }
}