package me.jameshunt.slideviewsspecial.bottom

import android.view.MotionEvent
import android.view.View
import me.jameshunt.slideviewsspecial.SlideOnTouch
import me.jameshunt.slideviewsspecial.SlideOnTouch.PlaceToSnap

/**
 * Created by James on 10/11/2017.
 */
class SlideBottomOnTouch(private val bottomView: View, private val topView: View): SlideOnTouch {

    private var startHeight = 0
    private var lastY = 0
    private var yStart = 0
    private var snapToLocation: PlaceToSnap = PlaceToSnap.BOTTOM

    override fun actionDown(event: MotionEvent) {
        startHeight = bottomView.height
        lastY = event.rawY.toInt()
        yStart = event.rawY.toInt()
    }

    override fun actionMove(event: MotionEvent) {
        val totalHeightDiff = yStart - event.rawY

        val bottomParams = bottomView.layoutParams
        bottomParams.height = startHeight + totalHeightDiff.toInt()
        bottomView.layoutParams = bottomParams

        snapToLocation = when (event.rawY > lastY) {
            true -> PlaceToSnap.BOTTOM
            false -> PlaceToSnap.TOP
        }

        lastY = event.rawY.toInt()
    }

    override fun actionUp(event: MotionEvent) {

    }
}