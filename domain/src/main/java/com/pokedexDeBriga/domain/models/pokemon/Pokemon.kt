package com.pokedexDeBriga.domain.models.pokemon

import com.pokedexDeBriga.domain.models.artwork.Sprite
import com.pokedexDeBriga.domain.models.types.Type

data class Pokemon (
    val nome: String = "",
    val id: Int = 0,
    val tipos: List<Type>? = null,
    var sprites: Sprite? = null
)

//TODO - Baseado no "PokemonModelo"

