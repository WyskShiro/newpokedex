package com.tem.plate.util.structure.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.tem.plate.pokemon.PokemonActivity
import com.tem.plate.util.extensions.observeEvent
import com.tem.plate.util.extensions.shortToast

abstract class BaseFragment : Fragment() {

    abstract val baseViewModel: BaseViewModel
    abstract val toolbarTitle: String

    private var dialog: Dialog? = null

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupToolbar()
        subscribeUi()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    open fun subscribeUi() {
        baseViewModel.toast.observeEvent(this, ::onNextToast)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onNextToast(text: String?) {
        text?.let {
            context?.shortToast(it)
        }
    }

    private fun setupToolbar() {
        (activity as? PokemonActivity)?.onNextTitle(toolbarTitle)
    }
}