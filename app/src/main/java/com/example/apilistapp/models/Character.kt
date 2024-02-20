package com.example.apilistapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterEntity")
data class Character(
    @PrimaryKey
    val id: Int = 0,
    val gender: String,
    val image: String,
    val name: String,
    val status: String,
)