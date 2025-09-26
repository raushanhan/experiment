package ru.kpfu.itis.springpractice.experiment.data.remote

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.kpfu.itis.springpractice.experiment.BuildConfig.ADVENTURER_APP_BASE_URL
import ru.kpfu.itis.springpractice.experiment.presentation.util.ImageLoaderInterface

class GlideImageLoader: ImageLoaderInterface {
    override fun loadImageIntoImageView(path: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(ADVENTURER_APP_BASE_URL + "files/download?path=" + path)
            .into(imageView)
    }
}