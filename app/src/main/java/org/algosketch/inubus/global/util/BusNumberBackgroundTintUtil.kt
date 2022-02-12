package org.algosketch.inubus.global.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import org.algosketch.inubus.R

object BusNumberBackgroundTintUtil {
    fun setBusNumberBackgroundTint(view: TextView, busNumber: String) {
        val busColor = Bus.getBusColorByBusNumber(busNumber)
        val colorMap = mapOf(
            "red" to R.color.red_bus,
            "blue" to R.color.blue_bus,
            "green" to R.color.green_bus,
            "purple" to R.color.purple_bus
        )

        setBackgroundTint(view, colorMap[busColor] ?: R.color.black_3)
    }

    fun setBackgroundTint(view: View, colorId: Int) {
        var drawable: Drawable? = view.background
        drawable = DrawableCompat.wrap(drawable!!)

        DrawableCompat.setTint(drawable, ContextCompat.getColor(view.context, colorId))
    }
}