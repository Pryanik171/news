package test.news.api.helper

import ru.digipeople.logger.LoggerFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Grigoriy Pryamov
 */
class DateConverter {

    private val logger = LoggerFactory.getLogger(DateConverter::class)

    private val dateFormat = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            return dateFormat
        }
    }

    fun entityToModel(entity: String): Long {
        return try {
            dateFormat.get()!!.parse(entity)!!.time
        } catch (e: ParseException) {
            logger.error("Parse date error", e)
            0L
        }
    }

    companion object {
        val INSTANCE: DateConverter = DateConverter()
    }
}