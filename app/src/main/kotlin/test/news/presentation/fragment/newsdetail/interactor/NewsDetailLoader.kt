package test.news.presentation.fragment.newsdetail.interactor

import io.reactivex.Single
import test.news.localdb.repository.NewsRepository

/**
 * Вспомогательный класс для загрузки детализации новости
 *
 * @author Grigoriy Pryamov
 */
class NewsDetailLoader(private val newsRepository: NewsRepository) {

    fun getLink(idNews: Long): Single<String> {
        return Single.fromCallable { newsRepository.getById(idNews) }
            .map { news -> return@map news.link }
    }
}