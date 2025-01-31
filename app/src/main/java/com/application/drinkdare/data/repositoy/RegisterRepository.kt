package com.application.drinkdare.data.repositoy

import com.application.drinkdare.data.remote.RegisterService

class RegisterRepository(private val registerService: RegisterService) {

    fun register(email: String, password: String): Boolean {
        // Aquí puede haber más lógica o validaciones antes de llamar al service
        return registerService.registerUser(email, password)
    }
}
