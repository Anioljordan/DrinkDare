package com.application.drinkdare.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.drinkdare.data.repositoy.CartaRepository
import com.application.drinkdare.domain.model.Carta
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartaViewModel @Inject constructor(private val cartaRepository: CartaRepository): ViewModel() {

    private val _cartas = MutableStateFlow<List<Carta>>(emptyList())
    val cartas: StateFlow<List<Carta>> = _cartas.asStateFlow()

    fun cargarCartas() {
        viewModelScope.launch {
            _cartas.value = cartaRepository.getCartas()
        }
    }
    fun crearCarta(carta: Carta) {
        viewModelScope.launch {
            val success = cartaRepository.createCarta(carta)
            if (success) cargarCartas()
        }
    }
    fun actualizarCarta(id: String, carta: Carta) {
        viewModelScope.launch {
            val success = cartaRepository.updateCarta(id, carta)
            if (success) cargarCartas()
        }
    }
    fun eliminarCarta(id: String) {
        viewModelScope.launch {
            val success = cartaRepository.deleteCarta(id)
            if (success) cargarCartas()
        }
    }
}