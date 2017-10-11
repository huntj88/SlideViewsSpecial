package me.jameshunt.slideviewsspecial

import android.view.MotionEvent

/**
 * Created by James on 10/11/2017.
 */
interface SlideOnTouch {

    fun actionDown(event: MotionEvent)

    fun actionMove(event: MotionEvent)

    fun actionUp(event: MotionEvent)

    enum class PlaceToSnap {
        TOP,
        BOTTOM
    }
}