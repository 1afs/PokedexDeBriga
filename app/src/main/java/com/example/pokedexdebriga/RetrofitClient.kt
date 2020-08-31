package com.example.pokedexdebriga

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Camada RetrofitClient

//Singleton
class RetrofitClient private constructor(){

    companion object{

        private lateinit var retrofit: Retrofit
        private val baseUrl = "http://jsonplaceholder.typicode.com/"

        private fun getRetrofitInstance(): Retrofit{

            val httpClient = OkHttpClient.Builder()
            if(!::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <T> criarServico(classeServico : Class <T> ) : T{
            return getRetrofitInstance().create(classeServico)
        }

    }

}