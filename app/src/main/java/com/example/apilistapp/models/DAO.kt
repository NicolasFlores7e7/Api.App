package com.example.apilistapp.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao{
    @Query("SELECT * FROM  CharacterEntity")
    suspend fun getAllCharacters(): MutableList<Character>
    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    suspend fun getCharacterById(id:Int): MutableList<Character>
    @Insert
    suspend fun addCharacter(character: Character)
    @Delete
    suspend fun deleteCharacter(character: Character)
}