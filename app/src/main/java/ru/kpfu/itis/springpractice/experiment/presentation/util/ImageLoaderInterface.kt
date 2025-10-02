package ru.kpfu.itis.springpractice.experiment.presentation.util

import android.widget.ImageView

interface ImageLoaderInterface {

    fun loadImageIntoImageView(path: String, imageView: ImageView)

}