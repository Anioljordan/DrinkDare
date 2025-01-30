package com.application.drinkdare.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CardRepository{
    private val db = FirebaseFirestore.getInstance()
    private val collectionRef = db.collection("cards")

    suspend fun addCard(card: Card){
        collectionRef.add(card).await()
    }
    suspend fun getCards(): List<Card>{
        return collectionRef.get().await().toObjects(Card::class.java)
    }
    suspend fun updateCard(id: String, newcard: Card){
        collectionRef.document(id).set(newcard).await()
    }
    suspend fun deleteCard(id: String){
        collectionRef.document(id).delete().await()
    }
}