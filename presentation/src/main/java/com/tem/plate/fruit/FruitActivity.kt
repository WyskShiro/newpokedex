package com.tem.plate.fruit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tem.plate.R
import com.tem.plate.databinding.ActivityFruitBinding
import com.tem.plate.util.structure.base.BaseActivity
import com.tem.plate.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class FruitActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityFruitBinding
    private lateinit var rxPermissions: RxPermissions
    private val viewModel: FruitViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fruit)
        lifecycle.addObserver(viewModel)
        setupUi()
        rxPermissions = RxPermissions(this)
        super.onCreate(savedInstanceState)
    }

    private fun setupUi() {
        // Set clicklisteners and textListeners
    }

    override fun subscribeUi() {
        super.subscribeUi()
        // Set listeners to observe viewmodel livedatas
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, FruitActivity::class.java)
        }
    }
}