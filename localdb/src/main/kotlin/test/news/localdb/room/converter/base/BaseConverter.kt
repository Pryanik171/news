package test.news.localdb.room.converter.base

/**
 * @author Grigoriy Pryamov
 */
interface BaseConverter<M, E> {

    fun entityToModel(entity: E): M

    fun modelToEntity(model: M): E

    fun entityListToModelList(entities: List<E>): List<M> {
        return arrayListOf<M>().apply {
            entities.forEach { E ->
                add(entityToModel(E))
            }
        }
    }

    fun modelListToEntityList(models: List<M>): List<E> {
        return arrayListOf<E>().apply {
            models.forEach { M ->
                add(modelToEntity(M))
            }
        }
    }
}