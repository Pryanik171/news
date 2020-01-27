package test.news.helper

import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Загрузчик изображений
 *
 * @author Grigoriy Pryamov
 */
class ImageLoader(
    private val imageView: ImageView,
    @DrawableRes private val placeholder: Int,
    private val imageUrl: String?
) {

    private val requestOptions
        get() = RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.RESOURCE)
            .placeholder(placeholder)
            .centerCrop()

    fun load() {
        onImageUriChanged()
    }

    private fun onImageUriChanged() {
        onImageViewSizeDetermined()
    }

    private fun onImageViewSizeDetermined() {
        Glide.with(imageView)
            .load(imageUrl)
            .apply(requestOptions)
            .into(imageView)
    }

    private val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (imageView.measuredHeight > 0) {
                onImageViewSizeDetermined()
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
    }

}