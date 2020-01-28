package test.news.presentation.fragment

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import test.news.presentation.fragment.newsdetail.NewsDetailFragment
import test.news.presentation.fragment.newsdetail.NewsDetailViewModel
import test.news.presentation.fragment.newsdetail.interactor.NewsDetailLoader
import test.news.presentation.fragment.newslist.NewsListFragment
import test.news.presentation.fragment.newslist.NewsListViewModel
import test.news.presentation.fragment.newslist.intercator.NewsListLoader

/**
 * @author Grigoriy Pryamov
 */
val fragmentModule = module {
    scope(named<NewsListFragment>()) {
        viewModel { NewsListViewModel(get(), get()) }
        scoped { NewsListLoader(get(), get(), get(), get()) }
    }

    scope(named<NewsDetailFragment>()) {
        viewModel { NewsDetailViewModel(get(), get()) }
        scoped { NewsDetailLoader(get()) }
    }
}