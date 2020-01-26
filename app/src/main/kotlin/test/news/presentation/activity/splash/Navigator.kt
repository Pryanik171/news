package test.news.presentation.activity.splash

import test.news.presentation.activity.base.BaseNavigator
import test.news.presentation.activity.main.MainActivity

/**
 * @author Grigoriy Pryamov
 */
class Navigator : BaseNavigator<SplashActivity>() {

    fun navigateToMainActivity() {
        safelyExecuteAction { activity ->
            activity.startActivity(MainActivity.callingIntent(activity))
            animForward(activity)
            activity.finish()
        }
    }
}