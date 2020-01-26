package test.news.localdb.room.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Базовый DAO.
 */
interface BaseDao<Entity> {
    /**
     * Вставляет [entity] в БД
     * @param entity Сущность
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entity: Entity): Long

    /**
     * Вставляет список [entities] в БД
     * @param entities Список сущностей
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entities: List<Entity>): LongArray

    /**
     * Обновляет [entity] в БД
     * @param entity Сущность
     */
    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(entity: Entity)

    /**
     * Обновляет список [entities] в БД
     * @param entities Список сущностей
     */
    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(entities: List<Entity>)

    /**
     * Удаляет [entity] из БД
     * @param entity Сущность
     */
    @Delete
    fun delete(entity: Entity)

    /**
     * Удаляет список [entities] из БД
     * @param entities Список сущностей
     */
    @Delete
    fun delete(entities: List<Entity>)
}