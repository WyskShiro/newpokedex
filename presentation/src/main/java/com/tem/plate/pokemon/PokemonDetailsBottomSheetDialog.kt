package com.tem.plate.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tem.domain.entity.Pokemon
import com.tem.plate.databinding.FragmentPokemonDetailsBinding

class PokemonDetailsBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        val pokemonReceived = arguments?.getSerializable(POKEMON_BUNDLE_KEY) as? Pokemon
        setupUi(pokemonReceived)
        return binding.root
    }

    private fun setupUi(pokemon: Pokemon?) {
        pokemon?.let { binding.pokemon = it }
    }

    companion object {
        const val TAG = "pokemon_details_bottom_sheet"
        const val POKEMON_BUNDLE_KEY = "POKEMON_BUNDLE_KEY"

        fun getInstance(bundle: Bundle? = null): PokemonDetailsBottomSheetDialog {
            return PokemonDetailsBottomSheetDialog().apply {
                arguments = bundle
            }
        }
    }
}