package ru.kpfu.itis.springpractice.experiment.presentation.util

import android.media.Image
import android.widget.ImageView
import javax.xml.transform.Source

interface ImageLoaderInterface {

    fun loadImageIntoImageView(path: String, imageView: ImageView)

}