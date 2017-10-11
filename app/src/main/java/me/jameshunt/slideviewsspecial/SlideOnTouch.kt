package me.jameshunt.slideviewsspecial

import android.view.MotionEvent

/**
 * Created by James on 10/11/2017.
 */
interface SlideOnTouch {

    fun actionDown(event: MotionEvent)

    fun actionMove(event: MotionEvent)

    fun actionUp(event: MotionEvent)

    fun getSnapToLocation(event: MotionEvent, lastY: Int, screenHeight: Int) : PlaceToSnap {

        val changeYTouch = event.rawY - lastY
        val changeYDistance = Dimensions.dpToPx(12)

        return when (event.rawY > lastY) {
            true -> {
                //finger going down
                if(event.rawY > screenHeight / 4 || changeYTouch > changeYDistance) PlaceToSnap.BOTTOM
                else PlaceToSnap.TOP
            }
            false -> {
                //finger going up
                if(event.rawY < screenHeight / 4 * 3 || changeYTouch < -changeYDistance) PlaceToSnap.TOP
                else PlaceToSnap.BOTTOM
            }
        }
    }

    enum class PlaceToSnap {
        TOP,
        BOTTOM
    }

    companion object SlideValues {
        val bottomHandleHeight:Int = Dimensions.dpToPx(28).toInt()
        val topHandleTitleHeight = Dimensions.dpToPx(60).toInt()
    }
}