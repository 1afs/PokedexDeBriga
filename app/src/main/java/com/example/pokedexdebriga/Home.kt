package com.example.pokedexdebriga

import android.content.Intent
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.net.rtp.AudioStream
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

import java.net.URL
import kotlin.Exception
import kotlin.properties.Delegates

class Home : AppCompatActivity() {

    var fraquezas: MutableSet<Any> = mutableSetOf()
    var resistencias: MutableSet<Any> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val conexao = RetrofitClient.criarServico(PokemonService::class.java)
        val tipo = RetrofitClient.criarServico(TipoService::class.java)
        val recebeIntent = intent.getStringExtra("nome")

        CoroutineScope(Dispatchers.Main).launch {
            try {
                pikachuLoader.visibility = View.VISIBLE
                mainContainer.visibility = View.GONE
                delay(3000)
                val info = withContext(Dispatchers.IO) {
                    conexao.list("pokemon/" + recebeIntent.toString().toLowerCase())
                }

                Picasso.get().load(info.sprites?.other?.artwork?.arte.toString())
                    .into(idHomeImageViewPokemon)
                val som =
                    executarSom("https://pokemoncries.com/cries/" + info.id.toString() + ".mp3")
                preencheDados(info)
                //   Picasso.get().load("file:///android_asset/" + "water.png").into(imagetest)

                if (info.tipos?.size!! > 1) {
                    // idHomeTipo2.setText(info.tipos?.get(1)?.types?.name)
                    puxaDados(tipo, 0, info)
                    puxaDados(tipo, 1, info)

                    var fraqueza = fraquezas.minus(resistencias)
                    var resistencia = resistencias.minus(fraquezas)

                    preencheSlotFraqueza(fraqueza)
                    preencheSlotResistencia(resistencia)

                } else {
                    puxaDados(tipo, 0, info)
                    preencheSlotFraqueza(fraquezas)
                    preencheSlotResistencia(resistencias)
                }
                idHomeButtonCry.setOnClickListener {
                    play(som)
                }
            } catch (e: Exception) {
                println(e.message)
                this@Home.finish()
            }
        }
    }

    suspend fun puxaDados(tipo: TipoService, index: Int, info: PokemonModelo) {

        val infoTipo = withContext(Dispatchers.IO) {
            tipo.lista("type/" + info.tipos?.get(index)?.types?.name)
        }

        infoTipo.relacaoDanos?.recebeEmDobro?.forEach { tipo ->
            //lista.add(tipo.name)
            fraquezas.add(tipo.name)
        }

        infoTipo.relacaoDanos?.recebeMetade?.forEach { tipo ->
            resistencias.add(tipo.name)
        }

        if (infoTipo.relacaoDanos?.anula != null) {
            infoTipo.relacaoDanos?.anula?.forEach { tipo ->
                resistencias.add(tipo.name)
            }
        }

        // Parei de implementar a resistencia... continuar
    }

    /*
    fun chamadoAPI(conecta : TipoService, url : String) : MutableList<TipoModelo.Damages>{

        val listaDeTipos : MutableList<TipoModelo.Damages> = mutableListOf()

        val chamado : Call<TipoModelo> = conecta.lista(url)
        val responseType = chamado.enqueue(object : Callback<TipoModelo>{
            override fun onResponse(call2: Call<TipoModelo>, response2: Response<TipoModelo>) {
                var infoTipo = response2.body()

                infoTipo?.relacaoDanos?.let { listaDeTipos.addAll(listOf(it)) }
                idF1.setText(infoTipo?.relacaoDanos?.recebeEmDobro?.get(0)?.name)

            }

            override fun onFailure(call: Call<TipoModelo>, t: Throwable) {
                val s = t.message
            }



        })
        return listaDeTipos

    }


     */
    fun preencheDados(info: PokemonModelo) {
        idHomeNomePokemon.setText(info.nome.toUpperCase())
        Picasso.get().load("file:///android_asset/" + info.tipos?.get(0)?.types?.name + ".png")
            .into(idHomeTipo1)
        idHomeTipo1.visibility = View.VISIBLE

        pikachuLoader.visibility = View.GONE
        mainContainer.visibility = View.VISIBLE

        if (info.tipos?.size!! > 1) {
            Picasso.get().load("file:///android_asset/" + info.tipos?.get(1)?.types?.name + ".png")
                .into(idHomeTipo2)
            idHomeTipo2.visibility = View.VISIBLE
        }
        //  idHomeIdPokemon.setText(info.id.toString())
        //  idHomeTipo1.setText(info.tipos?.get(0)?.types?.name)
    }

    fun preencheSlotFraqueza(info: Set<Any>) {

        info.forEach { any ->
            if (!idF1.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idF1)
                idF1.visibility = View.VISIBLE
            } else if (!idF2.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idF2)
                idF2.visibility = View.VISIBLE
            } else if (!idF3.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idF3)
                idF3.visibility = View.VISIBLE
            } else if (!idF4.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idF4)
                idF4.visibility = View.VISIBLE
            } else if (!idF5.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idF5)
                idF5.visibility = View.VISIBLE
            } else if (!idF6.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idF6)
                idF6.visibility = View.VISIBLE
            }
        }
    }

    fun preencheSlotResistencia(info: Set<Any>) {

        info.forEach { any ->
            if (!idR1.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idR1)
                idR1.visibility = View.VISIBLE
            } else if (!idR2.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idR2)
                idR2.visibility = View.VISIBLE
            } else if (!idR3.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idR3)
                idR3.visibility = View.VISIBLE
            } else if (!idR4.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idR4)
                idR4.visibility = View.VISIBLE
            } else if (!idR5.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idR5)
                idR5.visibility = View.VISIBLE
            } else if (!idR6.isVisible) {
                Picasso.get().load("file:///android_asset/$any.png").into(idR6)
                idR6.visibility = View.VISIBLE
            }
        }

    }

    fun play(media: MediaPlayer) {
        media.apply {
            start()
        }
    }

    fun executarSom(link: String): MediaPlayer {
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(link)
            prepare()
        }
        return mediaPlayer!!
    }
}
