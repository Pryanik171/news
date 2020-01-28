package test.news.localdb.transaction

/**
 * Транзакция БД.
 *
 */
interface DbTransaction {
    /**
     * Начинает транзакцию
     */
    fun begin()

    /**
     * Завершает транзакцию
     */
    fun end()

    /**
     * Применяет изменения
     */
    fun setSuccessful()

    /**
     * Выполняет блок кода в транзакции
     */
    fun <T> callInTx(body: () -> T): T
}