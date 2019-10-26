package com.tem.plate.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tem.plate.R
import com.tem.plate.databinding.ActivityPokemonBinding
import com.tem.plate.util.extensions.setupToolbar
import com.tem.plate.util.structure.base.BaseActivity
import com.tem.plate.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class PokemonActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityPokemonBinding
    private val viewModel: PokemonViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon)
        setupToolbar(binding.toolbar, false)
    }

    fun onNextTitle(title: String?) {
        title?.let {
            setupToolbar(
                binding.toolbar,
                true,
                it
            )
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, PokemonActivity::class.java)
        }
    }
}