package test.news.localdb.room.converter

import test.news.localdb.appmodel.News
import test.news.localdb.room.converter.base.BaseConverter
import test.news.localdb.room.entity.NewsEntity

/**
 * @author Grigoriy Pryamov
 */
class NewsConverter : BaseConverter<News, NewsEntity> {

    override fun entityToModel(entity: NewsEntity): News {
        return News(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            link = entity.link,
            image = entity.image,
            date = entity.date
        )
    }

    override fun modelToEntity(model: News): NewsEntity {
        return NewsEntity(
            id = model.id,
            title = model.title,
            description = model.description,
            link = model.link,
            image = model.image,
            date = model.date
        )
    }
}