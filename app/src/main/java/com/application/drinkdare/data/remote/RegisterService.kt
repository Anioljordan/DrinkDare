package com.application.drinkdare.data.remote


class RegisterService {

    // Simulación de registro
    fun registerUser(email: String, password: String): Boolean {
        // Aquí se hace la llamada a la API o lógica de negocio
        return email.isNotEmpty() && password.isNotEmpty() // Simple validación
    }
}
