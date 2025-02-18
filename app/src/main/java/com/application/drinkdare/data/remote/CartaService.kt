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
    suspend fun addCarta(carta: Carta): Boolean {
        return try {
            val documentRef = cartaCollection.add(carta).await()  // Agrega la carta
            val cartaConId = carta.copy(id = documentRef.id) // Obtén el ID generado y actualiza el objeto carta
            cartaCollection.document(documentRef.id).set(cartaConId).await() // Guarda el objeto con el ID correcto
            true
        } catch (e: Exception) {
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

}