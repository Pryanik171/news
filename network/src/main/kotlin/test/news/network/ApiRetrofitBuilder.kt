package test.news.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import test.news.network.model.ApiConfig

/**
 * @author Grigoriy Pryamov
 */
class ApiRetrofitBuilder(
    private val apiConfig: ApiConfig,
    private val httpClientBuilder: ApiHttpClientBuilder
) {

    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiConfig.url)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}