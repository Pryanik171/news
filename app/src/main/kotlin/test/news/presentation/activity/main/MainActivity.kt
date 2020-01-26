package test.news.presentation.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.scope.currentScope
import org.koin.core.KoinComponent
import test.news.R
import test.news.presentation.fragment.navigator.FragmentNavInitializer
import test.news.presentation.fragment.navigator.FragmentNavigator

/**
 * @author Grigoriy Pryamov
 */
class MainActivity : AppCompatActivity(), KoinComponent {

    private val fragmentNavInitializer by currentScope.inject<FragmentNavInitializer>()
    private val fragmentNavigator by currentScope.inject<FragmentNavigator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentNavInitializer.init(savedInstanceState)
        if (savedInstanceState == null) {
            fragmentNavigator.navigateToNewsList()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentNavInitializer.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        fragmentNavInitializer.onResume(this)
    }

    override fun onPause() {
        fragmentNavInitializer.onPause()
        super.onPause()
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }
}