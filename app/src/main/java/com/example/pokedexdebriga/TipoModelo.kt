package com.example.pokedexdebriga

import com.google.gson.annotations.SerializedName

class TipoModelo {

    @SerializedName("damage_relations")
    var relacaoDanos : Damages? = null



    class Damages {

        @SerializedName("double_damage_from")
        var recebeEmDobro : List<Tipo>? = null

        @SerializedName("half_damage_from")
        var recebeMetade : List<Tipo>? = null

        @SerializedName("no_damage_from")
        var anula : List<Tipo>? = null


        class Tipo {
            @SerializedName("name")
            var name : String = ""
        }

    }

}



