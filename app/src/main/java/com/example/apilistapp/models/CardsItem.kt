package com.example.apilistapp.models

data class CardsItem(
    val artist: String,
    val attack: Int,
    val cardId: String,
    val cost: Int,
    val elite: Boolean,
    val faction: String,
    val health: Int,
    val img: String,
    val mechanics: List<Mechanic>,
    val name: String,
    val playerClass: String,
    val race: String,
    val rarity: String,
    val spellSchool: String,
    val text: String,
    val type: String
)