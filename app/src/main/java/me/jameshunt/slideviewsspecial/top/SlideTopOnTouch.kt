package me.jameshunt.slideviewsspecial.top

import android.animation.ValueAnimator
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import me.jameshunt.slideviewsspecial.Dimensions
import me.jameshunt.slideviewsspecial.SlideOnTouch
import me.jameshunt.slideviewsspecial.SlideOnTouch.PlaceToSnap

/**
 * Created by James on 10/11/2017.
 */
class SlideTopOnTouch(private val topView: View, private val bottomView: View) : SlideOnTouch {

    private var startHeight = 0
    private var lastY = 0
    private var yStart = 0
    private var snapToLocation: PlaceToSnap = PlaceToSnap.TOP

    private var screenHeight = 0

    override fun actionDown(event: MotionEvent) {
        screenHeight = (topView.parent as FrameLayout).height

        startHeight = topView.height
        lastY = event.rawY.toInt()
        yStart = event.rawY.toInt()
    }

    override fun actionMove(event: MotionEvent) {
        val totalHeightDiff = event.rawY - yStart

        val topParams = topView.layoutParams
        topParams.height = Math.max(startHeight + totalHeightDiff.toInt(), 0)
        topView.layoutParams = topParams


        snapToLocation = getSnapToLocation(event, lastY, screenHeight)

        lastY = event.rawY.toInt()


        val bottomParams = bottomView.layoutParams
        bottomParams.height = getBottomHeight()

        bottomView.layoutParams = bottomParams

    }

    private fun getBottomHeight(): Int {

        val percent = (lastY - SlideOnTouch.topHandleTitleHeight) / screenHeight.toFloat()
        val heightRequest = Math.round(SlideOnTouch.bottomHandleHeight - (percent * SlideOnTouch.bottomHandleHeight))
        return Math.min(heightRequest, SlideOnTouch.bottomHandleHeight)
    }

    override fun actionUp(event: MotionEvent) {

        val startHeight = topView.height

        val endHeight: Int = when (snapToLocation) {
            PlaceToSnap.BOTTOM -> {
                screenHeight + Dimensions.dpToPx(60).toInt()
            }
            PlaceToSnap.TOP -> {
                Dimensions.dpToPx(60).toInt()
            }
        }

        val animation = ValueAnimator.ofObject(
                TopSlideHeightEval(topView = topView, bottomView = bottomView),
                startHeight,
                endHeight).setDuration(300)

        animation.start()

    }


}