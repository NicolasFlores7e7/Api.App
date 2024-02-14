package com.example.apilistapp.api

import com.example.apilistapp.models.HSCards
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface APIInterface {
    @GET("")
    suspend fun getCards(): Response<HSCards>

//    @GET("{name}")
//    suspend fun getCard(@Path("name") name: String):Response<Cards>

    companion object {
                val BASE_URL = "https://api.hearthstonejson.com/v1/"
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




