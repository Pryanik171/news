package test.news.network

import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import test.news.network.model.ApiConfig

/**
 * @author Grigoriy Pryamov
 */
fun getApiModule(apiConfig: ApiConfig): Module = module {

    single { apiConfig }
    single { OkHttpClient() }
    single { ApiHttpClientBuilder(get(), get()) }
    single { ApiRetrofitBuilder(get(), get()).build() }
    single { ResponseConverter() }
    //single { DefaultApiWorker(get(), get()) } bind ApiWorker::class
}