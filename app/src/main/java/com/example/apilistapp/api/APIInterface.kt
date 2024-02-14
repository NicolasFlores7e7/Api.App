package com.example.apilistapp.api

import com.example.apilistapp.models.Character
import com.example.apilistapp.models.HSCards
import com.example.apilistapp.models.Ninja
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface APIInterface {
    @GET("/characters")
    suspend fun getCharacters(): Response<Ninja>

//    @GET("{name}")
//    suspend fun getCard(@Path("name") name: String):Response<Cards>
    @GET ("/characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int) : Response<Ninja>

    companion object {
                val BASE_URL = "https://dattebayo-api.onrender.com"
//        fun crete(): APIInterface {
//            val client = OkHttpClient.Builder().apply {
//                addInterceptor(
//                    Interceptor {
//                        val builder = it.request().newBuilder()
//                        builder.header("X-RapidAPI-Key", "1d276f7c60msh0504c112c3ba8dep17473djsne1897790486d")
//                        builder.header("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
//                        return@Interceptor it.proceed(builder.build())
//                    }
//                )
//            }.build()
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//            return retrofit.create(APIInterface::class.java)
//
//        }

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




