package com.example.pokedexdebriga

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface TipoService {

    @GET

   suspend fun lista(@Url url: String): TipoModelo



}