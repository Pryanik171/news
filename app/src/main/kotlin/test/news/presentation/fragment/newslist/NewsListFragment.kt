package test.news.presentation.fragment.newslist

import android.os.Bundle
import android.util.Log
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import ru.digipeople.logger.LoggerFactory
import test.news.R
import test.news.presentation.fragment.base.MvvmFragment

/**
 * Экран со списком новостей
 *
 * @author Grigoriy Pryamov
 */
class NewsListFragment : MvvmFragment<NewsListViewModel>(R.layout.fragment_news_list),
    KoinComponent {

    private val logger = LoggerFactory.getLogger(NewsListFragment::class.java)
    override val viewModel: NewsListViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        logger.trace("onCreate")
        super.onCreate(savedInstanceState)
        viewModel.start()
    }

    companion object {
        fun newInstance() = NewsListFragment()
    }
}