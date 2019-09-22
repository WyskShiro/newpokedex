package com.tem.plate.pokemon

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.Pokemon

class PokemonListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var pokemons: MutableList<Pokemon> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonViewHolder.inflate(parent)
    }

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? PokemonViewHolder)?.setupBinding(pokemons[position])
    }

    fun insertPokemons(pokemons: List<Pokemon>) {
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }
}