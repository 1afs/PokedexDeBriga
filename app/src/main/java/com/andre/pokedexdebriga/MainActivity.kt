package com.andre.pokedexdebriga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = R.id.idCampoPesquisar

        idBotaoPesquisar.setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("nome", idCampoPesquisar.text.toString())
            }
            //startActivity(intent)
            executaTeste()
        }



    }

    fun executaTeste(){
        val conexao = RetrofitClient.criarServico(PokemonService::class.java)
        val tipo = RetrofitClient.criarServico(TipoService::class.java)
        val recebeIntent = intent.getStringExtra("nome")
    }
}
