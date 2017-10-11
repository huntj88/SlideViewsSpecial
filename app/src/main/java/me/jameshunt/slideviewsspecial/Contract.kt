package me.jameshunt.slideviewsspecial

/**
 * Created by James on 10/10/2017.
 */
interface Contract {

    interface View {

    }

    interface Presenter {
        var view: View

    }
}