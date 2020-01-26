package test.news.presentation.fragment.navigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

interface FragmentNavInitializer {
    fun init(savedInstanceState: Bundle?)
    fun onResume(activity: AppCompatActivity)
    fun onPause()
    fun onSaveInstanceState(outState: Bundle)
}
