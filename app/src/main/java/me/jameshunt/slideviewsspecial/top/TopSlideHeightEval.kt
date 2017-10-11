package me.jameshunt.slideviewsspecial.top

import android.animation.IntEvaluator
import android.view.View
import me.jameshunt.slideviewsspecial.Dimensions

/**
 * Created by James on 10/10/2017.
 */
class TopSlideHeightEval(private val topView: View, private val bottomView: View): IntEvaluator() {

    private var closedHeightOfBottom = Dimensions.dpToPx(56).toInt()
    private var startHeight = 0

    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {

        val topParams = topView.layoutParams
        topParams.height = super.evaluate(fraction, startValue, endValue)!!


        val bottomParams = bottomView.layoutParams

        startHeight = bottomParams.height

        if(startValue in closedHeightOfBottom until endValue)
            bottomParams.height = super.evaluate(fraction, startHeight, 0)
        else if(startValue > endValue)
            bottomParams.height = super.evaluate(fraction, startHeight, closedHeightOfBottom)


        bottomView.layoutParams = bottomParams
        topView.layoutParams = topParams

        return topParams.height
    }
}