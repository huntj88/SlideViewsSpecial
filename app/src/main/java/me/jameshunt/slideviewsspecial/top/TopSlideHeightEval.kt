package me.jameshunt.slideviewsspecial.top

import android.animation.IntEvaluator
import android.view.View
import me.jameshunt.slideviewsspecial.SlideOnTouch

/**
 * Created by James on 10/10/2017.
 */
class TopSlideHeightEval(private val topView: View, private val bottomView: View): IntEvaluator() {

    private var bottomStartHeight = 0

    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {

        val topParams = topView.layoutParams
        topParams.height = super.evaluate(fraction, startValue, endValue)


        val bottomParams = bottomView.layoutParams

        bottomStartHeight = bottomParams.height

        if(startValue in SlideOnTouch.topHandleTitleHeight until endValue)
            bottomParams.height = super.evaluate(fraction, bottomStartHeight, 0)
        else if(startValue > endValue)
            bottomParams.height = super.evaluate(fraction, bottomStartHeight, SlideOnTouch.bottomHandleHeight)


        bottomView.layoutParams = bottomParams
        topView.layoutParams = topParams

        return topParams.height
    }
}