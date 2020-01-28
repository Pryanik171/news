package test.news.presentation.fragment.newslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import ru.digipeople.logger.LoggerFactory
import test.news.R
import test.news.localdb.appmodel.NewsItem
import test.news.network.helper.model.UserError
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
    private lateinit var swipeRefresh: SwipeRefreshLayout
    //endregion

    private val logger = LoggerFactory.getLogger(NewsListFragment::class.java)
    override val viewModel: NewsListViewModel by currentScope.viewModel(this)
    private val newsListAdapter =
        NewsListAdapter(
            { idNews -> viewModel.onNewsItemClicked(idNews) },
            { viewModel.onSwipeRefresh() })

    override fun onCreate(savedInstanceState: Bundle?) {
        logger.trace("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.onSwipeRefresh()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(ItemDecoration(resources.getDimensionPixelOffset(R.dimen.item_top_offset)))
        recyclerView.adapter = newsListAdapter
        with(viewModel) {
            newsItemsLiveData.observe(this@NewsListFragment) { newsList ->
                setNews(newsList)
            }
            loadingLiveData.observe(this@NewsListFragment) { loading ->
                setLoading(loading)
            }
            userErrorLiveData.observe(this@NewsListFragment) { userError ->
                showError(userError)
            }
            start()
        }
    }

    private fun setOnScrollListenerEnable(isEnable: Boolean) {
        if (isEnable) {
            recyclerView.addOnScrollListener(onScrollListener)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        swipeRefresh.isRefreshing = isLoading
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == recyclerView.adapter!!.itemCount - 5) {
                logger.trace((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition().toString())
                recyclerView.removeOnScrollListener(this)
                viewModel.onEndList()
            }
        }
    }

    private fun setNews(listNews: List<NewsItem>) {
        logger.trace(listNews.size.toString())
        newsListAdapter.setList(listNews)
        setOnScrollListenerEnable(true)
    }

    private fun showError(userError: UserError) {
        Toast.makeText(requireActivity(), userError.message, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = NewsListFragment()
    }
}