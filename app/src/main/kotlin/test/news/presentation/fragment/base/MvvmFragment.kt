package test.news.presentation.fragment.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class MvvmFragment<ViewModel>(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    protected abstract val viewModel: ViewModel
}