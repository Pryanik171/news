package test.news.presentation.fragment.newslist

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.digipeople.logger.LoggerFactory
import test.news.localdb.appmodel.NewsItem
import test.news.presentation.fragment.navigator.FragmentNavigator
import test.news.presentation.fragment.newslist.intercator.NewsListLoader
import test.news.presentation.viewmodel.BaseViewModel
import test.news.presentation.viewmodel.SingleEventLiveData
import test.news.network.helper.model.UserError

/**
 * @author Grigoriy Pryamov
 */
class NewsListViewModel(
    private val loader: NewsListLoader,
    private val fragmentNavigator: FragmentNavigator
) : BaseViewModel() {

    private val logger = LoggerFactory.getLogger(NewsListViewModel::class.java)

    val newsItemsLiveData = MutableLiveData<List<NewsItem>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val userErrorLiveData = SingleEventLiveData<UserError>()

    private var isLoading = false

    override fun onStart() {
        logger.trace("onStart")
        loadNews(true)
    }

    private fun loadNews(pullToRefresh: Boolean) {
        loader.get(pullToRefresh)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading = true
                loadingLiveData.value = isLoading
            }
            .doOnTerminate {
                isLoading = false
                loadingLiveData.value = isLoading
            }
            .subscribe({ result ->
                if (!result.isSuccessful) {
                    userErrorLiveData.value = result.userError
                }
                newsItemsLiveData.value = result.newsList
            }, { error -> logger.error(error) })
            .disposeOnCleared()
    }

    fun onNewsItemClicked(newsId: Long) {
        fragmentNavigator.navigateToNewsDetail(newsId)
    }

    fun onEndList() {
        if (!isLoading) loadNews(false)
    }

    fun onSwipeRefresh() {
        loadNews(true)
    }
}