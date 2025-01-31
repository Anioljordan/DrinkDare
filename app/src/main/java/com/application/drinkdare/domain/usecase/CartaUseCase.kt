package com.application.drinkdare.domain.usecase

import com.application.drinkdare.data.repositoy.CartaRepository
import com.application.drinkdare.domain.model.Carta
import javax.inject.Inject

class CartaUseCase @Inject constructor(
    private val cartaRepository: CartaRepository
) {

    suspend fun obtenerCartas(): List<Carta> {
        return cartaRepository.getCartas()
    }

    suspend fun crearCarta(carta: Carta): Boolean {
        // Aquí puedes aplicar validaciones antes de guardar en Firestore
        if (carta.nombre.isBlank() || carta.descripcion.isBlank()) {
            return false // No guardar si falta información
        }
        return cartaRepository.createCarta(carta)
    }

    suspend fun actualizarCarta(id: String, carta: Carta): Boolean {
        return cartaRepository.updateCarta(id, carta)
    }

    suspend fun eliminarCarta(id: String): Boolean {
        return cartaRepository.deleteCarta(id)
    }
}