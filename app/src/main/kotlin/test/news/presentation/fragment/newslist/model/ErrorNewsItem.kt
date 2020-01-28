package test.news.presentation.fragment.newslist.model

import test.news.localdb.appmodel.NewsItem

/**
 * @author Grigoriy Pryamov
 */
data class ErrorNewsItem(
    val message: String
) : NewsItem