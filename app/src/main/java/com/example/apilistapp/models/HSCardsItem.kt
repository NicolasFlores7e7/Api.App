package com.example.apilistapp.models

data class HSCardsItem(
    val attack: Int,
    val collectible: Boolean,
    val cost: Int,
    val countAsCopyOfDbfId: Int,
    val dbfId: Int,
    val id: String,
    val name: String,
    val `set`: String,
    val text: String,
    val type: String
)