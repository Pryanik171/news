package test.news.localdb.room.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable
import test.news.localdb.room.dao.base.BaseDao
import test.news.localdb.room.entity.NewsEntity

/**
 * @author Grigoriy Pryamov
 */
@Dao
interface NewsDao : BaseDao<NewsEntity> {
    /**
     * Взвращает все NewsEntity
     */
    @Query("SELECT * FROM news")
    fun getAllRx(): Flowable<List<NewsEntity>>

    /**
     * Взвращает все NewsEntity
     */
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>

    /**
     * Взвращает entity по id
     */
    @Query("SELECT * FROM news WHERE id = :id")
    fun getById(id: Long): NewsEntity

    @Query("DELETE FROM news")
    fun deleteAll()
}