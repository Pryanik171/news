package test.news.presentation.fragment.newslist

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.digipeople.logger.LoggerFactory
import test.news.localdb.appmodel.News
import test.news.presentation.fragment.newslist.intercator.NewsListLoader
import test.news.presentation.viewmodel.BaseViewModel

/**
 * @author Grigoriy Pryamov
 */
class NewsListViewModel(
    private val loader: NewsListLoader
) : BaseViewModel() {

    private val logger = LoggerFactory.getLogger(NewsListViewModel::class.java)

    val newsItemsLiveData = MutableLiveData<List<News>>()

    override fun onStart() {
        logger.trace("onStart")
        loadNews()
    }

    private fun loadNews() {
        loader.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                newsItemsLiveData.value = list
            }, { error -> logger.error(error) })
            .disposeOnCleared()
    }
}