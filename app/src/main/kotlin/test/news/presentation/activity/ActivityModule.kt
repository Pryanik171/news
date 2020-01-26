package test.news.presentation.activity

import org.koin.core.qualifier.named
import org.koin.dsl.binds
import org.koin.dsl.module
import test.news.presentation.activity.main.MainActivity
import test.news.presentation.activity.splash.Navigator
import test.news.presentation.activity.splash.SplashActivity
import test.news.presentation.fragment.navigator.FragmentNavInitializer
import test.news.presentation.fragment.navigator.FragmentNavigator
import test.news.presentation.fragment.navigator.FragmentNavigatorImpl

/**
 * @author Grigoriy Pryamov
 */
val activityModule = module {

    scope(named<SplashActivity>()) {

    }

    scope(named<MainActivity>()) {

    }

    single { FragmentNavigatorImpl() } binds arrayOf(
        FragmentNavInitializer::class,
        FragmentNavigator::class
    )

    single { Navigator() }
}