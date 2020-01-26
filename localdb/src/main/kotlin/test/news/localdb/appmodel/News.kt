package test.news.localdb.appmodel

import test.news.localdb.appmodel.base.ModelWithId

/**
 * @author Grigoriy Pryamov
 */
class News(
    /**
     * Идентификатор карты
     */
    override val id: Long = 0L,
    /**
     * Title
     */
    val title: String,
    /**
     * Описание
     */
    val description: String,
    /**
     * Ссылка на детализацию
     */
    val link: String,
    /**
     * Ссылка на ищображение
     */
    val image: String,
    /**
     * Дата нововсти
     */
    val date: Long
) : ModelWithId<Long>