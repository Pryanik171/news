package test.news.utils.model

import test.news.network.helper.model.UserError

/**
 * Ошибка для отображения пользователю в UI.
 *
 * @author Grigoriy Pryamov
 */
interface UserError {
    /**
     * Текст сообщения
     */
    val message: String

    /**
     * Возможность повторить
     */
    val isRetry: Boolean

    companion object {
        val NO_ERROR: UserError = object : UserError {
            override val message = ""
            override val isRetry: Boolean = false
        }
    }
}
