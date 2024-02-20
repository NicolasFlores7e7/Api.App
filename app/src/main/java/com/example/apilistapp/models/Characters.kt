package com.example.apilistapp.models

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("results")
    val characters: List<Character>
)