package com.github.rubensousa.viewpagercards

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

class CardFragmentPagerAdapter(fm: FragmentManager, override val baseElevation: Float) : FragmentStatePagerAdapter(fm), CardAdapter {

    private val mFragments: MutableList<CardFragment>

    override val cardCount: Int
        get() = mFragments.size

    init {
        mFragments = ArrayList()

        for (i in 0..4) {
            addCardFragment(CardFragment())
        }
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mFragments[position].cardView
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        mFragments[position] = fragment as CardFragment
        return fragment
    }

    private fun addCardFragment(fragment: CardFragment) {
        mFragments.add(fragment)
    }

}
