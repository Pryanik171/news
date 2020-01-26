package test.news.presentation.fragment

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import test.news.presentation.fragment.newslist.NewsListFragment
import test.news.presentation.fragment.newslist.NewsListViewModel

/**
 * @author Grigoriy Pryamov
 */
val fragmentModule = module {
    scope(named<NewsListFragment>()) {
        viewModel { NewsListViewModel(get(), get()) }
    }
}