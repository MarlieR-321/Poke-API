package uca.ni.edu.pokemon.data

import com.google.gson.annotations.SerializedName

data class PokemonX(
    @SerializedName("id")
    var id:Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("base_experience")
    var base_experience: String,
    @SerializedName("height")
    var height: String,
    @SerializedName("weight")
    var weight: String
)