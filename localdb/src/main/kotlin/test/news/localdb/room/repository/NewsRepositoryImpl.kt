package test.news.localdb.room.repository

import io.reactivex.Observable
import test.news.localdb.DataBase
import test.news.localdb.appmodel.News
import test.news.localdb.repository.NewsRepository
import test.news.localdb.room.converter.NewsConverter
import test.news.localdb.room.entity.NewsEntity
import test.news.localdb.room.repository.base.ModelRepositoryImpl

/**
 * @author Grigoriy Pryamov
 */
class NewsRepositoryImpl(newsConverter: NewsConverter, dataBase: DataBase) :
    ModelRepositoryImpl<News, NewsEntity, Long>(dataBase), NewsRepository {

    override val dao = dataBase.newsDao()
    override val converter = newsConverter

    override fun getAllRx(): Observable<List<News>> {
       return dao.getAllRx()
           .map { entities -> converter.entityListToModelList(entities) }
           .toObservable()
    }
}