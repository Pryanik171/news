package test.news.presentation.fragment.newslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.news.R
import test.news.helper.ImageLoader
import test.news.localdb.appmodel.News
import test.news.localdb.appmodel.NewsItem
import test.news.presentation.fragment.newslist.model.ErrorNewsItem
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Grigoriy Pryamov
 */
class NewsListAdapter(
    private val itemMewsClicked: ((idNews: Long) -> Unit)?,
    private val itemRepeatClicked: (() -> Unit)?
) :
    ListAdapter<NewsItem, RecyclerView.ViewHolder>(diffUtilCallback) {

    private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        .apply { timeZone = TimeZone.getTimeZone("GMT") }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NEWS_ITEM_TYPE -> {
                NewsViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_news,
                        parent,
                        false
                    )
                )
            }
            NEWS_ERROR_ITEM_TYPE -> {
                NewsErrorViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_news_error,
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalAccessException("Item type not supported $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> holder.bind(getItem(position) as News)
            is NewsErrorViewHolder -> holder.bind(getItem(position) as ErrorNewsItem)
        }
    }

    fun setList(listNews: List<NewsItem>) {
        submitList(listNews)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is News -> NEWS_ITEM_TYPE
            is ErrorNewsItem -> NEWS_ERROR_ITEM_TYPE
            else -> throw IllegalAccessException("Item type not supported $position")
        }
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val description = itemView.findViewById<TextView>(R.id.description)
        private val date = itemView.findViewById<TextView>(R.id.date)
        private val image = itemView.findViewById<ImageView>(R.id.image)

        init {
            itemView.setOnClickListener {
                itemMewsClicked?.invoke((getItem(adapterPosition) as News).id)
            }
        }

        fun bind(item: News) {
            title.text = item.title
            description.text = item.description
            date.text = dateFormat.format(Date(item.date))
            ImageLoader(image, R.drawable.ll_bg_splash, item.image).load()
        }
    }

    inner class NewsErrorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val errorMessage = itemView.findViewById<TextView>(R.id.errorMessage)
        private val repeatBtn = itemView.findViewById<AppCompatButton>(R.id.repeatBtn)

        init {
            repeatBtn.setOnClickListener { itemRepeatClicked?.invoke() }
        }

        fun bind(item: ErrorNewsItem) {
            errorMessage.text = item.message
        }
    }

    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy"
        private const val NEWS_ITEM_TYPE = 0
        private const val NEWS_ERROR_ITEM_TYPE = 1
        private val diffUtilCallback = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
                if (oldItem::class != newItem::class) {
                    false
                } else {
                    when (oldItem) {
                        is News -> {
                            oldItem.id == (newItem as News).id
                        }
                        is ErrorNewsItem -> {
                            oldItem == newItem
                        }
                        else -> {
                            throw IllegalAccessException("OldItem not supported")
                        }
                    }
                }

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
                oldItem.equals(newItem)
        }
    }
}