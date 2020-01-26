package test.news.localdb.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import test.news.localdb.room.entity.base.EntityWithId

/**
 * @author Grigoriy Pryamov
 */
@Entity(tableName = "news")
class NewsEntity(
    /**
     * Id записи
     */
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0L,
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
) : EntityWithId<Long>