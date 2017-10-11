package me.jameshunt.slideviewsspecial.bottom

import android.animation.IntEvaluator
import android.util.Log
import android.view.View
import me.jameshunt.slideviewsspecial.Dimensions
import me.jameshunt.slideviewsspecial.SlideOnTouch

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

    private fun moveTop(fraction: Float ,startValue: Int, endValue: Int) {
        val topParams = topView.layoutParams

        topStartHeight = topParams.height

        //if(startValue > endValue) {
        if (startValue in endValue + 1 until (screenHeight - SlideOnTouch.bottomHandleHeight)) {
            Log.d("funky stuff", "first if bottom eval")
            topParams.height = super.evaluate(fraction, topStartHeight, Dimensions.dpToPx(60).toInt())
        } else if (startValue > SlideOnTouch.bottomHandleHeight) {
            Log.d("funky stuff", "second if bottom eval")
            topParams.height = super.evaluate(fraction, topStartHeight, 0)
        } else {
            Log.d("funky stuff", "neither bottom eval" + "     startValue: " + startValue + "      endValue: " + endValue + "    bottomHandleHeight" + SlideOnTouch.bottomHandleHeight)
        }


        topView.layoutParams = topParams
    }
}