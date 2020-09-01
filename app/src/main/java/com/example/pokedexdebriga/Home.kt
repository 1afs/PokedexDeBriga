package com.example.pokedexdebriga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val conexao = RetrofitClient.criarServico(PokemonService::class.java)
        val recebeIntent = intent.getStringExtra("nome")

        val call : Call<PokemonModelo> = conexao.list("pokemon/" + recebeIntent.toString().toLowerCase())

        val response = call.enqueue(object : Callback<PokemonModelo>{
            override fun onResponse(call: Call<PokemonModelo>, response: Response<PokemonModelo>) {
                val info = response.body()

                if (info != null) {
                    idHomeNomePokemon.setText(info.nome.toUpperCase())
                    idHomeIdPokemon.setText(info.id.toString())
                }

            }

            override fun onFailure(call: Call<PokemonModelo>, t: Throwable) {
                val s = t.message
                idHomeIdPokemon.setText("Deu ruim")

            }

        })


        idHomeNomePokemon.setText(recebeIntent)





    }
}