package com.example.apilistapp.api

class Repository {
    val apiInterface = APIInterface.create()
    suspend fun getAllCards() = apiInterface.getCards()
//    suspend fun getDetailedCard(id: String) = apiInterface.getCard(id)
}