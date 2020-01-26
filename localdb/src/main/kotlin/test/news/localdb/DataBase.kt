package test.news.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import test.news.localdb.room.dao.NewsDao
import test.news.localdb.room.entity.NewsEntity

/**
 * @author Grigoriy Pryamov
 */
@Database(
    version = BuildConfig.DataBaseVersion,
    exportSchema = false,
    entities = [NewsEntity::class]
)
abstract class DataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}