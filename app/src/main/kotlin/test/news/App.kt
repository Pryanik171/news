package test.news

import android.app.Application
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import test.news.di.appModule
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
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(
                listOf(
                    appModule,
                    activityModule,
                    fragmentModule,
                    getApiModule(
                        ApiConfig(
                            url = BuildConfig.BaseUrl,
                            key = BuildConfig.ApiKey,
                            startDate = BuildConfig.FromDate,
                            sortType = BuildConfig.SortType,
                            platform = BuildConfig.Platform
                        )
                    )
                )
            )
            if (BuildConfig.DEBUG) androidLogger()
        }
    }
}