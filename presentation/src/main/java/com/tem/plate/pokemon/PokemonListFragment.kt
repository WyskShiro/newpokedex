package com.tem.plate.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tem.domain.entity.Pokemon
import com.tem.plate.databinding.FragmentPokemonListBinding
import com.tem.plate.util.extensions.observeAction
import com.tem.plate.util.extensions.observeEvent
import com.tem.plate.util.structure.base.BaseFragment
import com.tem.plate.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class PokemonListFragment : BaseFragment() {
    override val toolbarTitle: String
        get() = "Listagem"

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonViewModel by inject()
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupUi()
        setupRecyclerAdapter()
        setupRecycler()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            pokemonList.observeAction(viewLifecycleOwner, ::updatePokemonList)
            pokemonDetails.observeAction(viewLifecycleOwner, ::showFragmentPokemonDetails)
            dialog.observeEvent(viewLifecycleOwner, ::onGetDialog)
        }
    }

    private fun setupUi() {
        // Set clicklisteners and textListeners
    }

    private fun setupRecyclerAdapter() {
        pokemonListAdapter = PokemonListAdapter(viewModel::onRecyclerItemClicked)
    }

    private fun setupRecycler() {
        with(binding.recyclerViewPokemon) {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonListAdapter
        }
    }

    // VIEW MODEL STUFF

    private fun updatePokemonList(pokemons: List<Pokemon>?) {
        pokemons?.run(pokemonListAdapter::insertPokemons)
    }

    private fun showFragmentPokemonDetails(pokemon: Pokemon?) {
        pokemon?.let {
            navController.navigate(
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsBottomSheetDialog(
                    it
                )
            )
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, PokemonListFragment::class.java)
        }
    }
}