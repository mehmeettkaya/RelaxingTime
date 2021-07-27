package com.mehmetkaya.relaxingtime.ui.main.home.epoxy

import androidx.annotation.StringRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.databinding.ItemHeaderBinding
import com.mehmetkaya.utils.epoxy.EpoxyItem
import com.mehmetkaya.utils.epoxy.ViewBindingEpoxyModel

@EpoxyModelClass
abstract class HeaderEpoxyModel : ViewBindingEpoxyModel<ItemHeaderBinding>() {

    @EpoxyAttribute
    lateinit var item: HeaderEpoxyItem

    override fun getDefaultLayout() = R.layout.item_header

    override fun ItemHeaderBinding.bind() {
        headerTextView.setText(item.titleRes)
    }
}

data class HeaderEpoxyItem(@StringRes val titleRes: Int) : EpoxyItem
