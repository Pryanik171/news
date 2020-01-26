package test.news.api.helper

import test.news.localdb.appmodel.News
import test.news.network.model.ArticleEntity

/**
 * Конвертер из Api entity в рабочую модельку
 *
 * @author Grigoriy Pryamov
 */
class NewsApiConverter {

    private fun entityToModel(entity: ArticleEntity): News {
        return News(
            title = entity.title,
            description = entity.description,
            link = entity.link,
            image = entity.image ?: "",
            date = DateConverter.INSTANCE.entityToModel(entity.date)
        )
    }

    fun entityListToModelList(entities: List<ArticleEntity>): List<News> {
        return arrayListOf<News>().apply {
            entities.forEach { entity ->
                add(entityToModel(entity))
            }
        }
    }

    companion object {
        val INSTANCE: NewsApiConverter = NewsApiConverter()
    }
}