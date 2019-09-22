package com.tem.plate.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.tem.domain.entity.Pokemon
import com.tem.plate.R
import com.tem.plate.databinding.ActivityPokemonBinding
import com.tem.plate.util.extensions.observe
import com.tem.plate.util.structure.base.BaseActivity
import com.tem.plate.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class PokemonListActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityPokemonBinding
    private val viewModel: PokemonViewModel by inject()
    private val pokemonListAdapter = PokemonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon)
        setupUi()
        setupRecycler()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.pokemonList.observe(this, ::updatePokemonList)
    }

    private fun setupUi() {
        // Set clicklisteners and textListeners
    }

    private fun setupRecycler() {
        with(binding.recyclerViewPokemon) {
            layoutManager = LinearLayoutManager(this@PokemonListActivity)
            adapter = pokemonListAdapter
        }
    }

    // VIEW MODEL STUFF

    private fun updatePokemonList(pokemons: List<Pokemon>?) {
        pokemons?.run(pokemonListAdapter::insertPokemons)
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, PokemonListActivity::class.java)
        }
    }
}