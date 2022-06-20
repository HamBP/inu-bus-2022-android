package org.algosketch.inubus.common.util

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun View.setBackgroundTint(colorId: Int) {
    var drawable: Drawable? = DrawableCompat.wrap(background!!)
    drawable = DrawableCompat.wrap(drawable!!)

    DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorId))
}