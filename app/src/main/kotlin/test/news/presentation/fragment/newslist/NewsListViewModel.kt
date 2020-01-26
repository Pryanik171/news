package test.news.presentation.fragment.newslist

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.digipeople.logger.LoggerFactory
import test.news.presentation.viewmodel.BaseViewModel

/**
 * @author Grigoriy Pryamov
 */
class NewsListViewModel : BaseViewModel() {

    private val logger = LoggerFactory.getLogger(NewsListViewModel::class.java)

    override fun onStart() {
        logger.trace("onStart")
    }

    private fun loadNews() {
        Single.fromCallable { "" }
            .observeOn(Schedulers.io())
            .subscribe({}, {})
            .disposeOnCleared()
    }
}