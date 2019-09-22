package com.tem.plate.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.Pokemon
import com.tem.plate.R
import com.tem.plate.databinding.ItemListPokemonBinding

class PokemonViewHolder(
    private val binding: ItemListPokemonBinding
): RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(pokemon: Pokemon) {
        binding.textViewName.text = pokemon.name
    }

    companion object {
        fun inflate(parent: ViewGroup?) = PokemonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.item_list_pokemon,
                parent,
                false
            )
        )
    }
}