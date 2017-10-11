package me.jameshunt.slideviewsspecial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_slide.*
import kotlinx.android.synthetic.main.middle_layout.*
import kotlinx.android.synthetic.main.top_slide.*
import me.jameshunt.slideviewsspecial.bottom.SlideBottomOnTouch
import me.jameshunt.slideviewsspecial.top.SlideTopOnTouch

class MainActivity : AppCompatActivity(), Contract.View {

    private val presenter = Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        addTopSlide()

        presenter.view = this

        handleSlideTop()
        handleSlideBottom()

        album_selected.setOnClickListener{
            presenter.albumSelected = !presenter.albumSelected
        }
    }

    private fun addTopSlide() {
        val topSlide = layoutInflater.inflate(R.layout.top_slide, null, false) as ConstraintLayout

        val screenHeight = resources.displayMetrics.heightPixels - Dimensions.dpToPx(24)

        val height = (screenHeight + Dimensions.dpToPx(60)).toInt()
        val width = resources.displayMetrics.widthPixels

        val params: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(width, height)
        topSlide.layoutParams = params

        main_frame.addView(topSlide)
    }

    private fun handleSlideTop() {
        val topSlideTouch: SlideOnTouch = SlideTopOnTouch(slide_view_top, slide_view_bottom, presenter)
        setupSlideTouchListener(slide_handle_top, topSlideTouch)
    }

    private fun handleSlideBottom() {
        val bottomSlideTouch = SlideBottomOnTouch(slide_view_bottom, slide_view_top, presenter)
        setupSlideTouchListener(slide_handle_bottom, bottomSlideTouch)
    }

    private fun setupSlideTouchListener(view: View, onTouch: SlideOnTouch) {
        view.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    onTouch.actionDown(event)
                }

                MotionEvent.ACTION_MOVE -> {
                    onTouch.actionMove(event)
                }

                MotionEvent.ACTION_CANCEL,
                MotionEvent.ACTION_UP -> {
                    onTouch.actionUp(event)
                }
            }

            true
        }
    }


}
