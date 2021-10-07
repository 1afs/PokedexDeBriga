package com.andre.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private val baseUrl = "https://pokeapi.co/api/v2/"

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)

            if (::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <T> criarServico(classeServico: Class<T>): T {
            return getRetrofitInstance().create(classeServico)
        }
    }
}
