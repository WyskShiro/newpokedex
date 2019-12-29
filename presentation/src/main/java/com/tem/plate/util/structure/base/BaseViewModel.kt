package com.tem.plate.util.structure.base

import androidx.lifecycle.*
import com.tem.plate.util.ErrorHandler
import com.tem.plate.util.structure.arch.Event
import com.tem.plate.util.structure.navigation.NavData
import com.tem.plate.util.viewmodels.DialogData
import com.tem.plate.util.viewmodels.Placeholder
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : LifecycleObserver, KoinComponent, ViewModel() {
    val goTo: LiveData<Event<NavData>> get() = goToLiveData
    val toast: LiveData<Event<String>> get() = toastLiveData
    val placeholder: LiveData<Placeholder> get() = placeholderLiveData
    val dialog: LiveData<Event<DialogData>> get() = dialogLiveData

    private val goToLiveData = MutableLiveData<Event<NavData>>()
    private val placeholderLiveData = MutableLiveData<Placeholder>()
    private val toastLiveData = MutableLiveData<Event<String>>()
    private val dialogLiveData = MutableLiveData<Event<DialogData>>()

    private val errorHandler: ErrorHandler by inject()

    fun setPlaceholder(placeholder: Placeholder) {
        placeholderLiveData.postValue(placeholder)
    }

    fun setToast(message: String) {
        toastLiveData.postValue(Event(message))
    }

    fun goTo(navData: NavData) {
        goToLiveData.postValue(Event(navData))
    }

    fun onFailure(throwable: Throwable) {
        setDialog(throwable)
    }

    fun setDialog(dialogData: DialogData) {
        dialogLiveData.postValue(Event(dialogData))
    }

    fun setDialog(
        throwable: Throwable,
        retryAction: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        setDialog(errorHandler.getDialogData(throwable, retryAction, onDismiss))
    }

    protected fun launchDataLoad(
        shouldLoad: Boolean = true,
        onFailure: (Throwable) -> Unit,
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                if (shouldLoad) setPlaceholder(Placeholder.Loading())
                block()
            } catch (error: Throwable) {
                onFailure(error)
            } finally {
                setPlaceholder(Placeholder.HideAll)
            }
        }
    }
}