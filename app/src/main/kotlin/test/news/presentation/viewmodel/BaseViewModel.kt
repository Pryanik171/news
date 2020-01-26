package test.news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Grigoriy Pryamov
 */
abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var isStarted: Boolean = false

    fun start() {
        if (!isStarted) {
            isStarted = true
            onStart()
        }
    }

    protected fun Disposable.disposeOnCleared() {
        compositeDisposable.add(this)
    }

    protected abstract fun onStart()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}