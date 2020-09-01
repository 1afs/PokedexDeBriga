package com.example.pokedexdebriga

import com.google.gson.annotations.SerializedName

class PokemonModelo {

    @SerializedName("name")
    var nome : String = ""

    @SerializedName("id")
    var id : Int = 0

}