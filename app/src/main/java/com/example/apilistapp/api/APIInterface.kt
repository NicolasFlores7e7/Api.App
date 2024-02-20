package com.example.apilistapp.api

import com.example.apilistapp.models.Characters
import com.example.apilistapp.models.Character
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface APIInterface {
    @GET("character")
    suspend fun getCharacters(): Response<Characters>

    @GET("character/{id}")

    suspend fun getCharacter(@Path("id") id:Int): Response<Character>


    companion object {
                val BASE_URL = "https://rickandmortyapi.com/api/"
//

        fun create(): APIInterface{
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}




