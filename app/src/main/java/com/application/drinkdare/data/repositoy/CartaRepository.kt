package com.application.drinkdare.data.repositoy

import com.application.drinkdare.data.remote.CartaService
import com.application.drinkdare.domain.model.Carta
import javax.inject.Inject

class CartaRepository @Inject constructor(private val cartaService: CartaService) {

        suspend fun createCarta(carta: Carta): Boolean {
            return cartaService.addCarta(carta)
        }

        suspend fun getCartas(): List<Carta> {
            return cartaService.getCartas()
        }


        suspend fun updateCarta(id: String, carta: Carta): Boolean {
            return cartaService.updateCarta(id, carta)
        }

        suspend fun deleteCarta(id: String): Boolean {
            return cartaService.deleteCarta(id)
        }
}