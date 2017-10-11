package me.jameshunt.slideviewsspecial

/**
 * Created by James on 10/10/2017.
 */
class Presenter: Contract.Presenter {

    override lateinit var view: Contract.View

    override var albumSelected: Boolean = false

    override fun isAlbumSelected(): Boolean {
        return albumSelected
    }
}