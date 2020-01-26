package test.news.localdb

import androidx.room.Room
import org.koin.dsl.module
import test.news.localdb.repository.NewsRepository
import test.news.localdb.room.converter.NewsConverter
import test.news.localdb.room.repository.NewsRepositoryImpl

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
    factory { NewsConverter() }
}
private const val DATABASE_NAME = "local_db"