package test.news.presentation.fragment.newslist

import io.reactivex.schedulers.Schedulers
import ru.digipeople.logger.LoggerFactory
import test.news.network.ApiWorker
import test.news.presentation.viewmodel.BaseViewModel

/**
 * @author Grigoriy Pryamov
 */
class NewsListViewModel(private val apiWorker: ApiWorker) : BaseViewModel() {

    private val logger = LoggerFactory.getLogger(NewsListViewModel::class.java)

    override fun onStart() {
        logger.trace("onStart")
        loadNews()
    }

    private fun loadNews() {
        apiWorker.getNews(1)
            .subscribeOn(Schedulers.io())
            .subscribe({}, {})
            .disposeOnCleared()
    }
}