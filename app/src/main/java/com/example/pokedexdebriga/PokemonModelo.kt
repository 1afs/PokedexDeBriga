package com.example.pokedexdebriga

import com.google.gson.annotations.SerializedName

class PokemonModelo {

    @SerializedName("name")
    var nome : String = ""

    @SerializedName("id")
    var id : Int = 0


    @SerializedName("types")
        var tipos : List<Tipos>? = null

    @SerializedName("sprites")
    var sprites : Sprites? = null

    //*################################################
    class Sprites {

        @SerializedName("other")
        var other : Others? = null

        class Others {

            @SerializedName("official-artwork")
            var artwork : Arte? = null

            class Arte {
                @SerializedName("front_default")
                var arte : String = ""
            }
        }

    }

    //*################################################

    class Tipos{
        @SerializedName("type")
        var types : Type? = null

        class Type{
            @SerializedName("name")
            var name : String = ""
            @SerializedName("url")
            var url : String = ""
        }
    }

    //*/################################################





}

// Fazer a adaptação de classes


/**
 * Cardapio seria o model para receber o conteúdo do cardapio

class Cardapio {
    @SerializedName("cardapio")
    private val conteudo: ArrayList<Conteudo>? = null

    /**
     * Este é um model que contem o conteúdo que do seu
     * cardapio que tipoprato e um array de pratos.
     */
    inner class Conteudo {
        @SerializedName("tipoprato")
        private val tipoprato: String? = null

        @SerializedName("pratos")
        private val pratos: ArrayList<Prato>? = null
    }

    /**
     * Prato é um model que receberá o nome do prato
     */
    inner class Prato {
        @SerializedName("prato")
        var prato: String? = null
    }
}
*/
