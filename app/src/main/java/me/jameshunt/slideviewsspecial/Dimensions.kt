package me.jameshunt.slideviewsspecial

import android.content.res.Resources

/**
 * Created by James on 10/10/2017.
 */
class Dimensions {

    companion object {
        fun dpToPx(dp: Int): Float {
            return (dp * Resources.getSystem().displayMetrics.density)
        }
    }
}