package me.jameshunt.slideviewsspecial

/**
 * Created by James on 10/10/2017.
 */
interface Contract {

    interface View {

    }

    interface Presenter: PresenterForSlide {
        var view: View
        var albumSelected: Boolean

    }

    interface PresenterForSlide {
        fun isAlbumSelected(): Boolean
    }
}