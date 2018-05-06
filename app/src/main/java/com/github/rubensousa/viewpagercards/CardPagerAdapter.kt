package com.github.rubensousa.viewpagercards

import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

class CardPagerAdapter : PagerAdapter(), CardAdapter {

    private val mViews: MutableList<CardView?>
    private val mData: MutableList<CardItem>
    override var baseElevation: Float = 0.toFloat()
    override val cardCount: Int
        get() = mData.size

    init {
        mData = ArrayList()
        mViews = ArrayList()
    }

    override fun getCount(): Int {
        return mData.size
    }

    fun addCardItem(item: CardItem) {
        mViews.add(null)
        mData.add(item)
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mViews[position]
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.adapter, container, false)
        container.addView(view)
        bind(mData[position], view)
        val cardView = view.findViewById<View>(R.id.cardView) as CardView

        if (baseElevation == 0f) {
            baseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = baseElevation * CardAdapter.MAX_ELEVATION_FACTOR
        mViews[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews.add(position, null)
    }

    private fun bind(item: CardItem, view: View) {

        val titleTextView = view.findViewById<View>(R.id.titleTextView) as TextView
        val contentTextView = view.findViewById<View>(R.id.contentTextView) as TextView
        titleTextView.setText(item.title)
        contentTextView.setText(item.text)
    }

}
