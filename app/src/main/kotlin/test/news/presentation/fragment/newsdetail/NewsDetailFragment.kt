package test.news.presentation.fragment.newsdetail

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import test.news.R
import test.news.presentation.fragment.base.MvvmFragment
import test.news.presentation.viewmodel.observe

/**
 * @author Grigoriy Pryamov
 */
class NewsDetailFragment : MvvmFragment<NewsDetailViewModel>(R.layout.fragment_news_detail),
    KoinComponent {

    //region View
    private lateinit var webView: WebView
    //endregion

    override val viewModel: NewsDetailViewModel by currentScope.viewModel(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webView)
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.settings.setAppCacheEnabled(true)
        with(viewModel) {
            newsId = arguments?.getLong(ARG_NEWS_ID)!!
            linkNewsLiveData.observe(this@NewsDetailFragment) { link ->
                loadUrl(link = link)
            }
            start()
        }
    }

    private fun loadUrl(link: String) {
        webView.loadUrl(link)
    }

    companion object {
        private const val ARG_NEWS_ID = "ARG_NEWS_ID"
        fun newInstance(newsId: Long) = NewsDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_NEWS_ID, newsId)
            }
        }
    }
}