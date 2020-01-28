package test.news.localdb.repository

import io.reactivex.Observable
import test.news.localdb.appmodel.News
import test.news.localdb.appmodel.NewsItem
import test.news.localdb.repository.base.ModelRepository

/**
 * @author Grigoriy Pryamov
 */
interface NewsRepository : ModelRepository<News, Long> {
    /**
     * Возвращает все News
     */
    fun getAllRx(): Observable<List<News>>

    /**
     * Возвращает все News
     */
    fun getAll():List<NewsItem>

    /**
     * Новость по локальному ID
     */
    fun getById(idNews: Long): News

    /**
     * Удаляет все записи
     */
    fun deleteAll()
}