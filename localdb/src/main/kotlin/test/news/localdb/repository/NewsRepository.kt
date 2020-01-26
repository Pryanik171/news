package test.news.localdb.repository

import io.reactivex.Observable
import test.news.localdb.appmodel.News
import test.news.localdb.repository.base.ModelRepository

/**
 * @author Grigoriy Pryamov
 */
interface NewsRepository : ModelRepository<News, Long> {
    /**
     * Возвращает все News
     */
    fun getAllRx(): Observable<List<News>>
}