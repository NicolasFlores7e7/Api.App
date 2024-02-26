package com.example.apilistapp.api

import com.example.apilistapp.models.Character
import com.example.apilistapp.models.CharacterApplication

class Repository {
    private val apiInterface = APIInterface.create()
    val daoInterface = CharacterApplication.database.characterDao()
    suspend fun getCharacters(page: Int) = apiInterface.getCharacters(page)
    suspend fun getCharacter(id:Int) = apiInterface.getCharacter(id)

    suspend fun saveAsFavorite(character:Character) = daoInterface.addCharacter(character)
    suspend fun deleteFavorite(character:Character) = daoInterface.deleteCharacter(character)
    suspend fun isFavorite(character: Character) = daoInterface.getCharacterById(character.id).isNotEmpty()
    suspend fun getFavorites()= daoInterface.getAllCharacters()

}