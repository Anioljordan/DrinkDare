package com.application.drinkdare.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.application.drinkdare.viewmodel.CardViewModel

@Composable
fun CardListScreen(viewModel: CardViewModel) {
val card by viewModel.card.collectAsState()
    LazyColumn {
        items(card) { card ->
        CardItem(card, viewModel)
        }
    }
}

@Composable
fun CardItem(card: Int, viewModel: Any) {
    TODO("Not yet implemented")
}
