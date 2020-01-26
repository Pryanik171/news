package test.news.presentation.activity.base

import androidx.appcompat.app.AppCompatActivity
import test.news.R
import java.util.*

/**
 * Базовый персональный активити навигатор
 *
 * @author Grigoriy Pryamov.
 */
abstract class BaseNavigator<A : AppCompatActivity> {

    private var baseActivity: A? = null
    private val actionList = LinkedList<(activity: A) -> Unit>()

    fun onResume(activity: A) {
        baseActivity = activity
        actionList.forEach { action -> action.invoke(baseActivity!!) }
        actionList.clear()
    }

    fun onPause() {
        baseActivity = null
    }

    /**
     * Выполняет операцию c проверкой активити на null
     *
     * @param action Операция для выполнения
     */
    protected fun safelyExecuteAction(action: (activity: A) -> Unit) {
        if (baseActivity != null) {
            action.invoke(baseActivity!!)
        } else {
            actionList.addLast(action)
        }
    }

    protected fun animForward(activity: A) {
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.zero_animation)
    }
}
