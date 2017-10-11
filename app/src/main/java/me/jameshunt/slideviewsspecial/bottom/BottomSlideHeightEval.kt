package me.jameshunt.slideviewsspecial.bottom

import android.animation.IntEvaluator
import android.view.View
import me.jameshunt.slideviewsspecial.SlideOnTouch
import me.jameshunt.slideviewsspecial.SlideOnTouch.SlideValues.getTopHandleHeightForBottomTouch

/**
 * Created by James on 10/11/2017.
 */
class BottomSlideHeightEval(private val bottomView: View, private val topView: View, private val screenHeight: Int) : IntEvaluator() {

    private var topStartHeight = 0

    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {

        val bottomParams = bottomView.layoutParams
        bottomParams.height = super.evaluate(fraction, startValue, endValue)

        moveTop(fraction, startValue, endValue)

        bottomView.layoutParams = bottomParams

        return bottomParams.height
    }

    private fun moveTop(fraction: Float, startValue: Int, endValue: Int) {
        val topParams = topView.layoutParams

        topStartHeight = topParams.height

        if (startValue in endValue + 1 until (screenHeight - SlideOnTouch.bottomHandleHeight)) {
            val topHandleHeight: Int = getTopHandleHeightForBottomTouch()
            topParams.height = super.evaluate(fraction, topStartHeight, topHandleHeight)
        } else if (startValue > SlideOnTouch.bottomHandleHeight) {
            topParams.height = super.evaluate(fraction, topStartHeight, 0)
        }


        topView.layoutParams = topParams
    }
}