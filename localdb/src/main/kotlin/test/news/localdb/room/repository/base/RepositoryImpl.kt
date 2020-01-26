package test.news.localdb.room.repository.base

import androidx.room.RoomDatabase
import test.news.localdb.repository.base.Repository

/**
 * Репозиторий.
 *
 * @author Aleksandr Brazhkin
 */
abstract class RepositoryImpl(protected val roomDb: RoomDatabase) : Repository {

    protected fun beginTransaction() {
        roomDb.beginTransaction()
    }

    protected fun endTransaction() {
        roomDb.endTransaction()
    }

    protected fun setTransactionSuccessful() {
        roomDb.setTransactionSuccessful()
    }
}