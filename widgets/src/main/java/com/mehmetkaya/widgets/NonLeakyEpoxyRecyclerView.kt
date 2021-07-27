package com.mehmetkaya.widgets

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyRecyclerView

class NonLeakyEpoxyRecyclerView(context: Context, attrs: AttributeSet) : EpoxyRecyclerView(context, attrs) {
    /**
     * Sharing a single view pool in the same activity make the fragments leak since the views inside the recyclerview
     * hold a reference to the fragments via callbacks. Returning false here makes EpoxyRecyclerView prevent
     * sharing view pool, which also prevents leaking fragments.
     */
    override fun shouldShareViewPoolAcrossContext() = false
}
