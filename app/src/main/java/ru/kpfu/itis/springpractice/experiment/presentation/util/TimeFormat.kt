package ru.kpfu.itis.springpractice.experiment.presentation.util

import android.content.Context
import android.text.format.DateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date


object MyDateTimeFormatter {

    fun format(context: Context, now: LocalDateTime): String {
        val date: Date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant())

        val formatted: String = DateFormat.getMediumDateFormat(context).format(date) + " " +
                DateFormat.getTimeFormat(context).format(date)

        return formatted
    }
}