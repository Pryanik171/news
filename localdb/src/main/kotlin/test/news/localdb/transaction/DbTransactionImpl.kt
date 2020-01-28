package test.news.localdb.transaction

import androidx.room.RoomDatabase

/**
 * Транзакция БД.
 *
 * @author Grigoriy Pryamov
 */
class DbTransactionImpl(private val roomDb: RoomDatabase) : DbTransaction {
    override fun begin() {
        roomDb.beginTransaction()
    }

    override fun end() {
        roomDb.endTransaction()
    }

    override fun setSuccessful() {
        roomDb.setTransactionSuccessful()
    }

    override fun <T> callInTx(body: () -> T): T {
        return roomDb.runInTransaction(body)
    }
}