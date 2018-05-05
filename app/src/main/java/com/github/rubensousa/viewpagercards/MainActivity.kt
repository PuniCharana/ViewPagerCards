package com.github.rubensousa.viewpagercards

import android.content.Context
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private var mCardAdapter: CardPagerAdapter? = null
    private var mCardShadowTransformer: ShadowTransformer? = null
    private var mFragmentCardAdapter: CardFragmentPagerAdapter? = null
    private var mFragmentCardShadowTransformer: ShadowTransformer? = null

    private var mShowingFragments = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkBox.setOnCheckedChangeListener(this)
        cardTypeBtn.setOnClickListener(this)

        mCardAdapter = CardPagerAdapter()
        mCardAdapter!!.addCardItem(CardItem(R.string.title_1, R.string.text_1))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_2, R.string.text_1))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_3, R.string.text_1))
        mCardAdapter!!.addCardItem(CardItem(R.string.title_4, R.string.text_1))
        mFragmentCardAdapter = CardFragmentPagerAdapter(supportFragmentManager,
                dpToPixels(2, this))

        mCardShadowTransformer = ShadowTransformer(viewPager, mCardAdapter!!)
        mFragmentCardShadowTransformer = ShadowTransformer(viewPager, mFragmentCardAdapter!!)

        viewPager.adapter = mCardAdapter
        viewPager.setPageTransformer(false, mCardShadowTransformer)
        viewPager.offscreenPageLimit = 3
    }

    override fun onClick(view: View) {
        if (!mShowingFragments) {
            cardTypeBtn.text = "Views"
            viewPager.adapter = mFragmentCardAdapter
            viewPager.setPageTransformer(false, mFragmentCardShadowTransformer)
        } else {
            cardTypeBtn.text = "Fragments"
            viewPager.adapter = mCardAdapter
            viewPager.setPageTransformer(false, mCardShadowTransformer)
        }

        mShowingFragments = !mShowingFragments
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
        mCardShadowTransformer!!.enableScaling(b)
        mFragmentCardShadowTransformer!!.enableScaling(b)
    }

    companion object {

        fun dpToPixels(dp: Int, context: Context): Float {
            return dp * context.resources.displayMetrics.density
        }
    }
}
