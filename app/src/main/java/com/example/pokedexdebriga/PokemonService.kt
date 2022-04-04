package com.example.pokedexdebriga

import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonService {
    // Camada PostService que mapeia a URL (possiveis parametros)
    @GET
    // fun list(""parametro""): Call<""retorno"">
    suspend fun list(@Url url: String): PokemonModelo
}
