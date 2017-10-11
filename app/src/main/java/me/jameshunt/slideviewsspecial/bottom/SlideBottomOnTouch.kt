package me.jameshunt.slideviewsspecial.bottom

import android.animation.ValueAnimator
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
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

    private var screenHeight = 0

    override fun actionDown(event: MotionEvent) {
        screenHeight = (topView.parent as FrameLayout).height

        startHeight = bottomView.height
        lastY = event.rawY.toInt()
        yStart = event.rawY.toInt()
    }

    override fun actionMove(event: MotionEvent) {
        val totalHeightDiff = yStart - event.rawY

        val bottomParams = bottomView.layoutParams
        bottomParams.height = Math.max(startHeight + totalHeightDiff.toInt(), 0)
        bottomView.layoutParams = bottomParams


        snapToLocation = getSnapToLocation(event, lastY, screenHeight)

        lastY = event.rawY.toInt()

        val topParams = topView.layoutParams
        topParams.height = getTopHeight()
        topView.layoutParams = topParams

        Log.d("y", event.rawY.toString() + "     " + bottomParams.height)
    }

    private fun getTopHeight(): Int {
        val percent = lastY / screenHeight.toFloat()
        val heightRequest = Math.round(percent * SlideOnTouch.topHandleTitleHeight)
        return Math.min(heightRequest, SlideOnTouch.topHandleTitleHeight)
    }

    override fun actionUp(event: MotionEvent) {
        val startHeight = bottomView.height

        val bottomSlideEval = BottomSlideHeightEval(bottomView = bottomView, topView = topView, screenHeight = screenHeight)

        val endHeight: Int = when (snapToLocation) {

            PlaceToSnap.BOTTOM -> {
                SlideOnTouch.bottomHandleHeight
            }

            PlaceToSnap.TOP -> {
                screenHeight - SlideOnTouch.bottomHandleHeight
            }
        }

        val animation = ValueAnimator.ofObject(
                bottomSlideEval,
                startHeight,
                endHeight).setDuration(300)

        animation.start()
    }
}