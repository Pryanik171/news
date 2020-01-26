package test.news.network.model

/**
 * Конфигурация API
 *
 * @author Grigoriy Pryamov
 */
class ApiConfig(
    /**
     * API URL
     */
    val url: String,
    /**
     * API KEY
     */
    val key: String,
    /**
     * Стартовая дата
     */
    val startDate: String,
    /**
     * Type сортировки
     */
    val sortType: String,
    /**
     * hard query
     */
    val platform: String
)