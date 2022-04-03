package uca.ni.edu.pokemon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import uca.ni.edu.pokemon.R
import uca.ni.edu.pokemon.data.PokemonX

class PokemonAdapter(private val list:ArrayList<PokemonX>, private val context:Context):RecyclerView.Adapter<PokemonAdapter.PokemonHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, null, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val current =list[position]
        holder.tvResultado.text =
            "Nombre: ${current.name}\n"+
            "Experiencia Base: ${current.base_experience}\n" +
            "Peso: ${current.weight} Kg\n" +
            "Altura: ${current.height} cm"

        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${current.id}.png")
            .centerCrop()
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.iv_pokemon)

    }

    override fun getItemCount(): Int = list.size

    class PokemonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvResultado: TextView = itemView.findViewById(R.id.item_list)
        val iv_pokemon:ImageView = itemView.findViewById(R.id.item_image)
    }

}