package test.news.presentation.fragment.navigator

interface FragmentNavigator {
    fun navigateToNewsList()
    fun navigateToNewsDetail(idNews: Long)
    fun navigateBack()
}
