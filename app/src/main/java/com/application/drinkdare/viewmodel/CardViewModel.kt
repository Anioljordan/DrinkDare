package com.application.drinkdare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.drinkdare.data.Card
import com.application.drinkdare.data.CardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardViewModel : ViewModel() {
    private val repository = CardRepository()
    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val persons: StateFlow<List<Card>> = _cards

    init {
        getCards()
    }

    fun addCard(card: Card) {
        viewModelScope.launch {
            repository.addCard(card)
            getCards()
        }
    }


        fun updateCard(id: String, newcard: Card) {
            viewModelScope.launch {
                repository.updateCard(id, newcard)
                getCards()
            }
        }

        fun deleteCard(id: String) {
            viewModelScope.launch {
                repository.deleteCard(id)
                getCards()
            }
        }
    fun getCards() {
        viewModelScope.launch {
            _cards.value = repository.getCards()
        }
    }
}