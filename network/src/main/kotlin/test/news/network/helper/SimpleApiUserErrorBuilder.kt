package test.news.network.helper

import android.content.Context
import ru.digipeople.logger.LoggerFactory
import test.news.network.R
import test.news.network.model.ApiError
import test.news.network.model.ApiException
import test.news.network.helper.model.UserError
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Билдер [UserError].
 *
 * @author Aleksandr Brazhkin
 */
open class SimpleApiUserErrorBuilder(
    private val context: Context,
    private val networkStateManager: NetworkStateManager
) {

    private val logger = LoggerFactory.getLogger(SimpleApiUserErrorBuilder::class)

    protected open val noConnectionMsg: String
        get() = context.getString(R.string.simple_api_error_no_internet_connection)

    protected open val unknownErrorMsg: String
        get() = context.getString(R.string.simple_api_error_unknown)

    open fun fromThrowable(throwable: Throwable): UserError {
        logger.error("fromThrowable", throwable)
        return if (throwable is ApiException) {
            if (throwable.statusCode in 502..504) {
                // 502 Bad Gateway («плохой, ошибочный шлюз»)
                // 503 Service Unavailable («сервис недоступен»)
                // 504 Gateway Timeout («шлюз не отвечает»)
                Error(unknownErrorMsg)
            } else {
                val apiError = throwable.apiError
                return if (apiError == null) {
                    Error(unknownErrorMsg)
                } else {
                    Error(unknownErrorMsg)
                }
            }
        } else if (throwable is SocketTimeoutException) {
            Error(unknownErrorMsg)
        } else if (throwable is ConnectException) {
            Error(noConnectionMsg, !networkStateManager.isOnline())
        } else if (throwable is SocketException) {
            Error(noConnectionMsg, !networkStateManager.isOnline())
        } else if (throwable is UnknownHostException) {
            Error(noConnectionMsg, !networkStateManager.isOnline())
        } else {
            Error(unknownErrorMsg)
        }
    }

    open fun fromApiError(apiError: ApiError): UserError {
           return Error(apiError.message)
    }

    private class Error(override val message: String, override val isRetry: Boolean = false) :
        UserError
}
