package com.application.drinkdare.presentation

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : androidx.lifecycle.ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                // Si el login es exitoso, se invoca el callback con true
                callback(true)
            } catch (e: Exception) {
                // Si ocurre algún error, invoca el callback con false
                callback(false)
            }
        }
    }
}
