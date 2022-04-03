package uca.ni.edu.pokemon

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import uca.ni.edu.pokemon.data.PokemonX

interface ApiService {
    @GET()
    suspend fun getDatosPokemon(@Url url:String):Response<PokemonX>
}