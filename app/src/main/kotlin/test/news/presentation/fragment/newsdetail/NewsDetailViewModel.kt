package test.news.presentation.fragment.newsdetail

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.digipeople.logger.LoggerFactory
import test.news.presentation.fragment.navigator.FragmentNavigator
import test.news.presentation.fragment.newsdetail.interactor.NewsDetailLoader
import test.news.presentation.viewmodel.BaseViewModel

/**
 * @author Grigoriy Pryamov
 */
class NewsDetailViewModel(
    private val detailLoader: NewsDetailLoader,
    private val fragmentNavigator: FragmentNavigator
) : BaseViewModel() {

    private val logger = LoggerFactory.getLogger(NewsDetailViewModel::class.java)

    var newsId: Long = 0L
    val linkNewsLiveData = MutableLiveData<String>()

    override fun onStart() {
        logger.trace("onStart")
        detailLoader.getLink(newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ link ->
                linkNewsLiveData.value = link
            }, { error -> logger.error(error) })
            .disposeOnCleared()
    }
}