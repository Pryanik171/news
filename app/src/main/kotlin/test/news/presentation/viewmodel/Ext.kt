package test.news.presentation.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observe(fragment: Fragment, onChanged: (T) -> Unit) {
    this.observe({ fragment.lifecycle }, onChanged)
}