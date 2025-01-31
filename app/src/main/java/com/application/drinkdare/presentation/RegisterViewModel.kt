package com.application.drinkdare.presentation

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel : androidx.lifecycle.ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun register(email: String, password: String, confirmPassword: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            if (password != confirmPassword) {
                callback(false) // Si las contraseñas no coinciden, retornar falso
                return@launch
            }

            try {
                // Crear un usuario en Firebase Authentication
                val result = auth.createUserWithEmailAndPassword(email, password).await()

                // Guardar el usuario en Firestore (si es necesario)
                val user = hashMapOf(
                    "email" to email
                )
                db.collection("users").document(result.user?.uid ?: "").set(user).await()

                callback(true) // Si todo salió bien, llamar al callback con true
            } catch (e: Exception) {
                callback(false) // Si hubo algún error, llamar al callback con false
            }
        }
    }
}
