package com.tem.plate.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.Pokemon
import com.tem.plate.R
import com.tem.plate.databinding.ItemListPokemonBinding

class PokemonViewHolder(
    private val binding: ItemListPokemonBinding,
    private val onClickCallback: (Pokemon) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(pokemon: Pokemon) {
        binding.pokemon = pokemon
        binding.constraintLayoutItem.setOnClickListener{
            onClickCallback.invoke(pokemon)
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?, onClickCallback: (Pokemon) -> Unit) = PokemonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.item_list_pokemon,
                parent,
                false
            ),
            onClickCallback
        )
    }
}