package test.news.presentation.activity.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.scope.currentScope
import org.koin.core.KoinComponent

/**
 * Стартовый экран
 *
 * @author Grigoriy Pryamov
 */
class SplashActivity : AppCompatActivity(), KoinComponent {

    private val navigator by currentScope.inject<Navigator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.navigateToMainActivity()
    }

    override fun onResume() {
        super.onResume()
        navigator.onResume(this)
    }

    override fun onPause() {
        navigator.onPause()
        super.onPause()
    }
}