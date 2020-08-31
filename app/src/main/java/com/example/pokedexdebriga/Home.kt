package com.example.pokedexdebriga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recebeIntent = intent.getStringExtra("nome")

        idHomeNomePokemon.setText(recebeIntent)

    }
}