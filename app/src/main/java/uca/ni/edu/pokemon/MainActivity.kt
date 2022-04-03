package uca.ni.edu.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uca.ni.edu.pokemon.adapters.PokemonAdapter
import uca.ni.edu.pokemon.data.PokemonX
import uca.ni.edu.pokemon.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var poke:ArrayList<PokemonX> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuscar.setOnClickListener {
            buscarDatosPokemon()
        }

        binding.btnClear.setOnClickListener {
            poke.clear()
            binding.rvPokemon.layoutManager = LinearLayoutManager(applicationContext)
            val adapter = PokemonAdapter(poke,applicationContext)

            binding.rvPokemon.adapter = adapter
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun buscarDatosPokemon(){

        CoroutineScope(Dispatchers.Main).launch {
            try{
                val txtFiltro = "pokemon/${binding.etName.text}"
                val call=getRetrofit().create(ApiService::class.java).getDatosPokemon(txtFiltro)

                if(call.isSuccessful){
                    var pokemonx: PokemonX? =call.body()
                    pokemonx?.id = binding.etName.text.toString().toInt()

                    poke.add(0,pokemonx!!)

                    binding.rvPokemon.layoutManager = LinearLayoutManager(applicationContext)
                    val adapter = PokemonAdapter(poke,applicationContext)

                    binding.rvPokemon.adapter = adapter
                }
            }catch (ex:Exception){
                val msg = Toast.makeText(this@MainActivity,"Error de conexion",Toast.LENGTH_LONG)
                msg.setGravity(Gravity.CENTER, 0,0)
                msg.show()
            }
        }
    }
}