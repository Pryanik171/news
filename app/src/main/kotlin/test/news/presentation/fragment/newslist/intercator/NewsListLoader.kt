package test.news.presentation.fragment.newslist.intercator

import io.reactivex.Completable
import test.news.api.helper.NewsApiConverter
import test.news.localdb.repository.NewsRepository
import test.news.network.ApiWorker

/**
 * Вспомогательный класс для загрузки новостей
 *
 * @author Grigoriy Pryamov
 */
class NewsListLoader(
    private val apiWorker: ApiWorker,
    private val newsRepository: NewsRepository
) {
    private val converter = NewsApiConverter.INSTANCE

    fun get(): Completable {
        return apiWorker.getNews(1)
            .doOnSuccess { response ->
                newsRepository.insert(converter.entityListToModelList(response.data.list))
            }.toCompletable()
    }
}