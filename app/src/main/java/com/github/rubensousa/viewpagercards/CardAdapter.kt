package com.github.rubensousa.viewpagercards


import android.support.v7.widget.CardView

interface CardAdapter {

    val baseElevation: Float

    val cardCount: Int

    fun getCardViewAt(position: Int): CardView?

    companion object {

        val MAX_ELEVATION_FACTOR = 8
    }
}
