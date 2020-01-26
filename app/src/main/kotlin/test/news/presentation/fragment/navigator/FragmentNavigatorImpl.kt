package test.news.presentation.fragment.navigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import test.news.R
import test.news.presentation.fragment.newslist.NewsListFragment
import java.util.*

/**
 * @author Grigoriy Pryamov
 */
class FragmentNavigatorImpl : FragmentNavInitializer,
    FragmentNavigator {


    private var _activity: AppCompatActivity? = null
    private val activity
        get() = _activity!!
    private val fragmentManager
        get() = activity.supportFragmentManager
    private val containerId = R.id.activity_main_container
    private val buffer = LinkedList<() -> Unit>()
    private val isResumed
        get() = _activity != null
    private val screenStack = LinkedList<String>()

    override fun init(savedInstanceState: Bundle?) {
        savedInstanceState?.getBundle(SS_FRAGMENT_NAVIGATOR_BUNDLE)?.let { bundle ->
            val stackArray = bundle.getStringArray(SS_STACK)
            screenStack.clear()
            screenStack.addAll(stackArray!!)
        }
    }

    override fun onResume(activity: AppCompatActivity) {
        this._activity = activity
        while (buffer.isNotEmpty()) {
            buffer.pop()()
        }
    }

    override fun onPause() {
        this._activity = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val bundle = Bundle().apply {
            putStringArray(SS_STACK, Array(screenStack.size) { screenStack[it] })
        }
        outState.putBundle(SS_FRAGMENT_NAVIGATOR_BUNDLE, bundle)
    }

    private fun newChain(fragment: () -> Fragment, screenKey: ScreenKey) {
        post {
            if (isCurrent(screenKey.name)) {
                return@post
            }
            forwardInternal(fragment, screenKey.name)
        }
    }

    override fun navigateToNewsList() {
        newChain({ NewsListFragment.newInstance() }, ScreenKey.NEWS_LIST)
    }

    override fun navigateToNewsDetail(idNews: Long) {

    }

    override fun navigateBack() {
        post {
            fragmentManager.popBackStack()
        }
    }

    private fun forward(fragment: () -> Fragment, screenKey: ScreenKey) {
        post {
            if (isCurrent(screenKey.name)) {
                return@post
            }
            forwardInternal(fragment, screenKey.name)
        }
    }

    private fun forwardInternal(fragment: () -> Fragment, screenKey: String) {
        fragmentManager
            .beginTransaction()
            .replace(containerId, fragment(), screenKey)
            .addToBackStack(screenKey)
            .commit()
        screenStack.push(screenKey)
    }

    private fun isCurrent(screenKey: String): Boolean {
        val count = fragmentManager.backStackEntryCount
        if (count > 0) {
            val entry = fragmentManager.getBackStackEntryAt(count - 1)
            if (entry.name == screenKey || screenStack.peek() == screenKey) {
                return true
            }
        }
        return false
    }

    private fun post(func: () -> Unit) {
        if (isResumed) {
            func()
        } else {
            buffer.addLast(func)
        }
    }

    companion object {
        const val SS_FRAGMENT_NAVIGATOR_BUNDLE = "SS_FRAGMENT_NAVIGATOR_BUNDLE"
        const val SS_STACK = "SS_STACK"
    }
}

enum class ScreenKey {
    NEWS_LIST, NEWS_DETAIL
}