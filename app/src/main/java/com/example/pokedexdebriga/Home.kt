package com.example.pokedexdebriga

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.rtp.AudioStream
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val conexao = RetrofitClient.criarServico(PokemonService::class.java)
        //val getCry = RetrofitClient.getCry(PokemonCry::class.java)

        val recebeIntent = intent.getStringExtra("nome")

        val call : Call<PokemonModelo> = conexao.list("pokemon/" + recebeIntent.toString().toLowerCase())



        val response = call.enqueue(object : Callback<PokemonModelo>{
            override fun onResponse(call: Call<PokemonModelo>, response: Response<PokemonModelo>) {
                val info = response.body()

                if (info != null) {
                    Picasso.get().load(info.sprites?.other?.artwork?.arte.toString()).into(idHomeImageViewPokemon)
                    idHomeNomePokemon.setText(info.nome.toUpperCase())
                    idHomeIdPokemon.setText(info.id.toString())
                    idHomeTipo1.setText(info.tipos?.get(0)?.types?.name)
                    if (info.tipos?.size!! > 1) {
                        idHomeTipo2.setText(info.tipos?.get(1)?.types?.name)
                    }

                    idHomeButtonCry.setOnClickListener {
                        executarSom("https://pokemoncries.com/cries/" + info.id.toString() + ".mp3")
                    }
                   // executarSom("https://pokemoncries.com/cries/" + info.id.toString() + ".mp3")

                   // val cry : Unit = getCry.cry(info.id.toString() + ".mp3")



                    
                }

            }

            override fun onFailure(call: Call<PokemonModelo>, t: Throwable) {
                val s = t.message
                idHomeIdPokemon.setText("Sorry amigão, esse nome está incorreto")

            }

        })

        idHomeNomePokemon.setText(recebeIntent)


    }

    fun executarSom(link : String){

        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(link)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }
}