package com.application.drinkdare.data.remote

import com.application.drinkdare.domain.model.Carta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CartaService (private val firestore: FirebaseFirestore){
    private val cartaCollection = firestore.collection("carta")

    suspend fun getCartas(): List<Carta>{
        return try {
            val snapshot = cartaCollection.get().await()
            snapshot.toObjects(Carta::class.java)
        } catch (e: Exception){
            emptyList()
        }
    }
        suspend fun addCarta(carta: Carta): Boolean{
            return try {
                cartaCollection.add(carta).await()
                true
            } catch (e: Exception){
                false
            }
        }
    suspend fun updateCarta(id: String, carta: Carta): Boolean{
        return try {
            cartaCollection.document(id).set(carta).await()
            true
        } catch (e: Exception){
            false
        }
    }
    suspend fun deleteCarta(id: String): Boolean{
        return try {
            cartaCollection.document(id).delete().await()
            true
        } catch (e: Exception){
            false
        }
    }
    suspend fun addCartaWithIncrement(carta: Carta): Boolean{
        return try {
            val snapshot = cartaCollection.get().await()
            val lastId = snapshot.documents.mapNotNull { it.id.toIntOrNull() }.maxOrNull() ?: 0
            val newCarta = carta.copy(id = (lastId + 1))
            cartaCollection.document(newCarta.id.toString()).set(newCarta).await()
            true
        } catch (e: Exception){
            false
        }
    }
}