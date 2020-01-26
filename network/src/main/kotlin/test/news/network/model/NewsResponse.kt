package test.news.network.model

import com.google.gson.annotations.SerializedName

/**
 * Ответ сервера
 *
 * @author Grigoriy Pryamov
 */
class NewsResponse(
    /**
     * Статус
     */
    @SerializedName("status")
    val status: String,
    /**
     * Общее-кол-во найденых записей
     */
    @SerializedName("totalResults")
    val total: Long,
    /**
     * Список новостей
     */
    @SerializedName("articles")
    val list: MutableList<ArticleEntity>
)

class ArticleEntity(
    /**
     * Источник
     */
    @SerializedName("source")
    val source: Source,
    /**
     * Автор
     */
    @SerializedName("author")
    val author: String? = "",
    /**
     * Title
     */
    @SerializedName("title")
    val title: String,
    /**
     * Описание
     */
    @SerializedName("description")
    val description: String,
    /**
     * Ссылка на детализацию
     */
    @SerializedName("url")
    val link: String,
    /**
     * Ссылка на ищображение
     */
    @SerializedName("urlToImage")
    val image: String,
    /**
     * Дата нововсти
     */
    @SerializedName("publishedAt")
    val date: String,
    /**
     * Наполнение
     */
    @SerializedName("content")
    val content: String
)

class Source(
    /**
     * Id источника данных
     */
    val id: Long? = null,
    /**
     * Наименование
     */
    val name: String? = ""
)