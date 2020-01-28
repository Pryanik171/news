package test.news.presentation.fragment.newslist.intercator

import io.reactivex.Observable
import io.reactivex.Single
import test.news.api.helper.NewsApiConverter
import test.news.localdb.appmodel.News
import test.news.localdb.appmodel.NewsItem
import test.news.localdb.repository.NewsRepository
import test.news.localdb.transaction.DbTransaction
import test.news.network.ApiWorker
import test.news.network.helper.SimpleApiUserErrorBuilder
import test.news.network.helper.model.UserError
import test.news.presentation.fragment.newslist.model.ErrorNewsItem

/**
 * Вспомогательный класс для загрузки новостей
 *
 * @author Grigoriy Pryamov
 */
class NewsListLoader(
    private val apiWorker: ApiWorker,
    private val newsRepository: NewsRepository,
    private val dbTransaction: DbTransaction,
    private val userErrorBuilder: SimpleApiUserErrorBuilder
) {
    private val converter = NewsApiConverter.INSTANCE
    private var currentPage = DEFAULT_PAGE

    fun get(fetchCache: Boolean): Observable<Result> {
        return if (fetchCache)
            loadFromCache()
                .concatWith(loadFromApi(true))
        else {
            loadFromApi(false)
        }
    }

    private fun loadFromApi(isFirst: Boolean): Observable<Result> {
        return apiWorker.getNews(currentPage)
            .map { response -> converter.entityListToModelList(response.data.list) }
            .doOnSuccess { newsList -> dbTransaction.callInTx { store(isFirst, newsList) } }
            .doOnSuccess { currentPage++ }
            .flatMap { Single.fromCallable { newsRepository.getAll() } }
            .map { newsList -> Result(newsList, UserError.NO_ERROR) }
            .onErrorReturn { error ->
                //???
                val userError = userErrorBuilder.fromThrowable(error)
                val cacheList = newsRepository.getAll().toMutableList()
                    .apply {
                        if (userError.isRetry) {
                            add(ErrorNewsItem(userError.message))
                        }
                    }
                return@onErrorReturn Result(cacheList, userError)
            }
            .toObservable()
    }

    private fun loadFromCache(): Observable<Result> {
        return Observable.fromCallable { newsRepository.getAll() }
            .map { newsList -> Result(newsList, UserError.NO_ERROR) }
    }

    private fun store(isReset: Boolean, newsList: List<News>) {
        if (isReset) newsRepository.deleteAll()
        newsRepository.insert(newsList)
    }

    class Result(
        val newsList: List<NewsItem>,
        val userError: UserError,
        val isSuccessful: Boolean = userError == UserError.NO_ERROR
    )

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PAGE_LIST_COUNT = 20
        private const val MAX_PAGE = 5
    }
}