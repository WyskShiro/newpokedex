package com.tem.plate.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tem.domain.entity.Pokemon
import com.tem.plate.databinding.FragmentPokemonDetailsBinding

class PokemonDetailsBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding

    private val args: PokemonDetailsBottomSheetDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        val pokemonReceived = args.pokemon
        setupUi(pokemonReceived)
        return binding.root
    }

    private fun setupUi(pokemon: Pokemon?) {
        pokemon?.let { binding.pokemon = it }
    }
}