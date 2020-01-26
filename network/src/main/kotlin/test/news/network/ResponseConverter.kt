package test.news.network

import retrofit2.Retrofit
import ru.digipeople.logger.LoggerFactory
import test.news.network.model.ApiError
import test.news.network.model.ApiException
import test.news.network.model.Response

/**
 *  Конвертер ответа с сервера в формате retrofit [retrofit2.Response]
 *  во внутренний формат [Response] или в сущность ответа.
 *
 * @author Grigoriy Pryamov.
 */
class ResponseConverter() {

    private val logger = LoggerFactory.getLogger(ResponseConverter::class)

    /**
     * Конвертирует [retrofit2.Response] в сущность типа [E].
     *
     * @param retrofitResponse Ответ сервера в формате [Retrofit]
     * @param <E>              Тип сущности сервера
     * @return Ответ сервера в виде сущности типа [E]
     * @throws ApiException В случае ошибки  ковертации*/
    @Throws(ApiException::class)
    fun <E : Response<E>> convertToEntity(retrofitResponse: retrofit2.Response<E>): E {
        val response = convert(retrofitResponse)
        if (response.isSuccessful) {
            return response.data
        } else {
            throw ApiException(response.code, response.error)
        }
    }

    /**
     * Конвертирует [retrofit2.Response] в [Response].
     *
     * @param retrofitResponse Ответ сервера в формате [Retrofit]
     * @param <E>              Тип сущности сервера
     * @return Ответ сервера в формате [Response]
     * @throws ApiException В случае ошибки  ковертации*/
    @Throws(ApiException::class)
    fun <E> convert(retrofitResponse: retrofit2.Response<E>): Response<E> {
        if (retrofitResponse.isSuccessful) {
            // Если запрос завершился успешно
            val data = retrofitResponse.body()
            return Response<E>(data, null, retrofitResponse.code())
        } else {
            val code = retrofitResponse.code()
            logger.error("Error api error: $code")
            throw ApiException(code, ApiError(code, retrofitResponse.message()))
        }
    }
}