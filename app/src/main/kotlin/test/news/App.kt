package test.news

import android.app.Application
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import test.news.di.appModule
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
                    fragmentModule
                )
            )
            if (BuildConfig.DEBUG) androidLogger()
        }
    }
}