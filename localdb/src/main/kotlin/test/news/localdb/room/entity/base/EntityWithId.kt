package test.news.localdb.room.entity.base

/**
 * Сущность локальной БД с Id.
 */
interface EntityWithId<Id> {
    var id: Id
}