package test.news.presentation.fragment.newslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import ru.digipeople.logger.LoggerFactory
import test.news.R
import test.news.localdb.appmodel.News
import test.news.presentation.fragment.base.MvvmFragment
import test.news.presentation.fragment.newslist.adapter.ItemDecoration
import test.news.presentation.fragment.newslist.adapter.NewsListAdapter
import test.news.presentation.viewmodel.observe


/**
 * Экран со списком новостей
 *
 * @author Grigoriy Pryamov
 */
class NewsListFragment : MvvmFragment<NewsListViewModel>(R.layout.fragment_news_list),
    KoinComponent {

    //region View
    private lateinit var recyclerView: RecyclerView
    //endregion

    private val logger = LoggerFactory.getLogger(NewsListFragment::class.java)
    override val viewModel: NewsListViewModel by currentScope.viewModel(this)
    private val newsListAdapter = NewsListAdapter {}

    override fun onCreate(savedInstanceState: Bundle?) {
        logger.trace("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(ItemDecoration(resources.getDimensionPixelOffset(R.dimen.item_top_offset)))
        recyclerView.adapter = newsListAdapter
        with(viewModel) {
            newsItemsLiveData.observe(this@NewsListFragment) { newsList ->
                setNews(newsList)
            }
            start()
        }
    }

    private fun setNews(listNews: List<News>) {
        newsListAdapter.setList(listNews)
    }

    companion object {
        fun newInstance() = NewsListFragment()
    }
}