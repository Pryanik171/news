package test.news.localdb.room.repository.base

import androidx.room.RoomDatabase
import test.news.localdb.appmodel.base.ModelWithId
import test.news.localdb.repository.base.ModelRepository
import test.news.localdb.room.converter.base.BaseConverter
import test.news.localdb.room.dao.base.BaseDao
import test.news.localdb.room.entity.base.EntityWithId

/**
 * Базовый репозиторий для моделей с Id.
 */
abstract class ModelRepositoryImpl<Model : ModelWithId<Id>, Entity : EntityWithId<Id>, Id> protected constructor(
    appDatabase: RoomDatabase
) : RepositoryImpl(appDatabase),
    ModelRepository<Model, Id> {

    abstract val dao: BaseDao<Entity>

    abstract val converter: BaseConverter<Model, Entity>

    override fun insert(model: Model) {
        val entity = converter.modelToEntity(model)
        dao.insert(entity)
    }

    override fun insert(models: List<Model>) {
        val entities = converter.modelListToEntityList(models)
        dao.insert(entities)
    }

    override fun update(model: Model) {
        dao.update(converter.modelToEntity(model))
    }

    override fun update(models: List<Model>) {
        dao.update(converter.modelListToEntityList(models))
    }

    override fun delete(model: Model) {
        dao.delete(converter.modelToEntity(model))
    }

    override fun delete(models: List<Model>) {
        val entities = converter.modelListToEntityList(models)
        dao.delete(entities)
    }
}
