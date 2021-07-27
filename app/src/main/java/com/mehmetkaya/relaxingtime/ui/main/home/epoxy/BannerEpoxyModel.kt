package com.mehmetkaya.relaxingtime.ui.main.home.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.databinding.ItemBannerBinding
import com.mehmetkaya.utils.epoxy.EpoxyItem
import com.mehmetkaya.utils.epoxy.ViewBindingEpoxyModel

@EpoxyModelClass
abstract class BannerEpoxyModel : ViewBindingEpoxyModel<ItemBannerBinding>() {

    @EpoxyAttribute
    lateinit var item: BannerEpoxyItem

    override fun getDefaultLayout() = R.layout.item_banner

    override fun ItemBannerBinding.bind() {
        val bannerText = root.context.getString(R.string.banner_text, item.userName)
        bannerTextView.text = bannerText
    }
}

data class BannerEpoxyItem(val userName: String) : EpoxyItem
