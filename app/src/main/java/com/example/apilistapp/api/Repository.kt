package com.example.apilistapp.api

class Repository {
    val apiInterface = APIInterface.crete()
    suspend fun getAllVanillaCards() = apiInterface.getCards()
}