package test.news.localdb

import androidx.room.Room
import org.koin.dsl.module
import test.news.localdb.repository.NewsRepository
import test.news.localdb.room.converter.NewsConverter
import test.news.localdb.room.repository.NewsRepositoryImpl
import test.news.localdb.transaction.DbTransaction
import test.news.localdb.transaction.DbTransactionImpl

/**
 * @author Grigoriy Pryamov
 */
val localDbModule = module {

    single {
        Room.databaseBuilder(get(), DataBase::class.java, DATABASE_NAME)
            .build()
    }

    single<NewsRepository> {
        NewsRepositoryImpl(get(), get())
    }

    single<DbTransaction> { DbTransactionImpl(get<DataBase>()) }

    factory { NewsConverter() }
}
private const val DATABASE_NAME = "local_db"