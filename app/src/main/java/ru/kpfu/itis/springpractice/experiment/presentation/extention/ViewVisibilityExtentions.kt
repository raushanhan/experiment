package ru.kpfu.itis.springpractice.experiment.presentation.extention

import android.view.View

fun View.show() { visibility = View.VISIBLE }
fun View.hide() { visibility = View.GONE }
fun View.invisible() { visibility = View.INVISIBLE }