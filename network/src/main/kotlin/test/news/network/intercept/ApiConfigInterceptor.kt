package test.news.network.intercept

import okhttp3.Interceptor
import okhttp3.Response
import test.news.network.model.ApiConfig

/**
 * @author Grigoriy Pryamov
 */
class ApiConfigInterceptor(private val apiConfig: ApiConfig) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request =  chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter(QUERY, apiConfig.platform)
            .addQueryParameter(FROM_DATE, apiConfig.startDate)
            .addQueryParameter(SORT, apiConfig.sortType)
            .addQueryParameter(API_KEY, apiConfig.key)
            .build()
        return chain.proceed(chain.request()
            .newBuilder()
            .url(url)
            .build())
    }

    companion object {
        private const val QUERY = "q"
        private const val FROM_DATE = "from"
        private const val SORT = "sortBy"
        private const val API_KEY = "apiKey"
    }
}