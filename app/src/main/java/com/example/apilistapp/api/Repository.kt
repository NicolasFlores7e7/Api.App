package com.example.apilistapp.api

import com.example.apilistapp.viewmodel.APIViewModel

class Repository {
    val apiInterface = APIInterface.create()
    suspend fun getAllNinjas() = apiInterface.getCharacters()
    suspend fun getDetailedNinja(id: Int) = apiInterface.getCharacter(0)
}