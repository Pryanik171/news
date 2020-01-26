package test.news.network.model

/**
 * Http ответ API.
 * Абстракция над [retrofit2.Response].
 *
 * @author Grigoriy Pryamov.
 */
class Response<T>(private val _data: T?, private val _error: ApiError?, val code: Int) {
    val data
        get() = _data!!
    val error
        get() = _error!!
    val isSuccessful
        get() = code in 200..299
}