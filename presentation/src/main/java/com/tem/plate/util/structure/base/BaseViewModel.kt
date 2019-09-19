package com.tem.plate.util.structure.base

import androidx.lifecycle.*
import com.tem.plate.util.structure.arch.Event
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : LifecycleObserver, KoinComponent, ViewModel() {
    val goTo: LiveData<Event<NavData>> get() = goToLiveData
    val dialog: LiveData<Event<DialogData>> get() = dialogLiveData
    val toast: LiveData<Event<String>> get() = toastLiveData
    val placeholder: LiveData<Placeholder> get() = placeholderLiveData

    private val goToLiveData = MutableLiveData<Event<NavData>>()
    private val dialogLiveData = MutableLiveData<Event<DialogData>>()
    private val placeholderLiveData = MutableLiveData<Placeholder>()
    private val toastLiveData = MutableLiveData<Event<String>>()
    private val errorHandler: ErrorHandler by inject()

    protected val disposables: CompositeDisposable = CompositeDisposable()

    fun setPlaceholder(placeholder: Placeholder) {
        placeholderLiveData.postValue(placeholder)
    }

    fun setPlaceholder(throwable: Throwable, retryAction: (() -> Unit)?) {
        setPlaceholder(errorHandler.getPlaceholder(throwable, retryAction))
    }

    fun setDialog(dialogData: DialogData) {
        dialogLiveData.postValue(Event(dialogData))
    }

    fun setToast(message: String) {
        toastLiveData.postValue(Event(message))
    }

    fun setDialog(
        throwable: Throwable, retryAction: (() -> Unit)? = null, onDismiss: (() -> Unit)? = null
    ) {
        setDialog(errorHandler.getDialogData(throwable, retryAction, onDismiss))
    }

    fun goTo(navData: NavData) {
        goToLiveData.postValue(Event(navData))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        disposables.dispose()
    }
}