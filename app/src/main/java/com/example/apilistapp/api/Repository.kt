package com.example.apilistapp.api

import com.example.apilistapp.viewmodel.APIViewModel

class Repository {
    private val apiInterface = APIInterface.create()

    suspend fun getCharacters() = apiInterface.getCharacters()
    suspend fun getCharacter(id:Int) = apiInterface.getCharacter(id)

}