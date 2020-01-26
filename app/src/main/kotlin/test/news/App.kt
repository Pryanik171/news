package test.news

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.digipeople.database.sqlitestudioservice.SQLiteStudioService
import test.news.di.appModule
import test.news.localdb.localDbModule
import test.news.network.getApiModule
import test.news.network.model.ApiConfig
import test.news.presentation.activity.activityModule
import test.news.presentation.fragment.fragmentModule

/**
 * @author Grigoriy Pryamov
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initExternalSQLiteTool(this)
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    getApiModule(
                        ApiConfig(
                            url = BuildConfig.BaseUrl,
                            key = BuildConfig.ApiKey,
                            startDate = BuildConfig.FromDate,
                            sortType = BuildConfig.SortType,
                            platform = BuildConfig.Platform
                        )
                    ),
                    localDbModule,
                    activityModule,
                    fragmentModule

                )
            )
            if (BuildConfig.DEBUG) androidLogger()
        }
    }

    private fun initExternalSQLiteTool(appContext: Context) {
        SQLiteStudioService(appContext, BuildConfig.SQLITE_STUDIO_SERVICE_PORT).start()
    }
}