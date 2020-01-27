package test.news.presentation.fragment.newslist.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Grigoriy Pryamov
 */
class ItemDecoration(private val topSpacing: Int) : RecyclerView.ItemDecoration() {

    private val side = topSpacing / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = topSpacing
        outRect.left = side
        outRect.right = side
    }
}