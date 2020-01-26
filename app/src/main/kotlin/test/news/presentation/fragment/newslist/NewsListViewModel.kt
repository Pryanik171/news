package test.news.presentation.fragment.newslist

import io.reactivex.schedulers.Schedulers
import ru.digipeople.logger.LoggerFactory
import test.news.presentation.fragment.newslist.intercator.NewsListLoader
import test.news.presentation.viewmodel.BaseViewModel

/**
 * @author Grigoriy Pryamov
 */
class NewsListViewModel(
    private val loader: NewsListLoader
) : BaseViewModel() {

    private val logger = LoggerFactory.getLogger(NewsListViewModel::class.java)

    override fun onStart() {
        logger.trace("onStart")
        loadNews()
    }

    private fun loadNews() {
        loader.get()
            .subscribeOn(Schedulers.io())
            .subscribe({}, { error -> logger.error(error) })
            .disposeOnCleared()
    }
}