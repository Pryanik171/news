package test.news.presentation.fragment.newslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.news.R
import test.news.helper.ImageLoader
import test.news.localdb.appmodel.News

/**
 * @author Grigoriy Pryamov
 */
class NewsListAdapter(private val itemMewsClicked: (idNews: Long) -> Unit) :
    ListAdapter<News, RecyclerView.ViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(getItem(position))
    }

    fun setList(listNews: List<News>) {
        submitList(listNews)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val description = itemView.findViewById<TextView>(R.id.description)
        private val date = itemView.findViewById<TextView>(R.id.date)
        private val image = itemView.findViewById<ImageView>(R.id.image)

        fun bind(item: News) {
            title.text = item.title
            description.text = item.description
            date.text = item.date.toString()
            ImageLoader(image, R.drawable.ll_bg_splash, item.image).load()
        }
    }

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.equals(newItem)
        }
    }
}