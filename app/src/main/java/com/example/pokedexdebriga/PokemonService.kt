package com.example.pokedexdebriga

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import java.net.URL
import retrofit2.http.Path as Path

interface PokemonService {


        //Camada PostService que mapeia a URL (possiveis parametros)
        @GET
        //fun list(""parametro""): Call<""retorno"">
        fun list(@Url url: String): Call<PokemonModelo>


}