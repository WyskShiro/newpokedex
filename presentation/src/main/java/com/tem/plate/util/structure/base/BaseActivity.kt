package com.tem.plate.util.structure.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.tem.plate.util.extensions.observeEvent
import com.tem.plate.util.extensions.shortToast
import com.tem.plate.util.structure.navigation.NavData
import com.tem.plate.util.structure.navigation.Navigator

abstract class BaseActivity : AppCompatActivity() {

    abstract val baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(baseViewModel)
        subscribeUi()
    }

    open fun subscribeUi() {
        with(baseViewModel) {
            goTo.observeEvent(this@BaseActivity, ::onNextNavigation)
            toast.observeEvent(this@BaseActivity, ::onNextToast)
        }
    }

    private fun onNextToast(text: String?) {
        text?.let {
            shortToast(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onNextNavigation(navData: NavData?) {
        navData?.let {
            Navigator.goTo(this, it)
        }
    }
}