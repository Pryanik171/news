package test.news.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import test.news.network.intercept.ApiConfigInterceptor
import test.news.network.model.ApiConfig
import java.util.concurrent.TimeUnit

/**
 * @author Grigoriy Pryamov.
 */
class ApiHttpClientBuilder(
    private val okHttpClient: OkHttpClient,
    private val apiConfig: ApiConfig
) {

    fun build(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return okHttpClient.newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiConfigInterceptor(apiConfig))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    companion object {
        /**
         * Таймаут на подключение/чтение/запись (в секундах)
         */
        const val TIMEOUT = 35L
    }
}