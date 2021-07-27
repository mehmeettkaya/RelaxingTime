package com.mehmetkaya.relaxingtime.ui.main.home.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.data.remote.home.Meditation
import com.mehmetkaya.relaxingtime.databinding.ItemMeditationBinding
import com.mehmetkaya.utils.epoxy.EpoxyItem
import com.mehmetkaya.utils.epoxy.ViewBindingEpoxyModel
import com.mehmetkaya.utils.exts.load

@EpoxyModelClass
abstract class MeditationEpoxyModel : ViewBindingEpoxyModel<ItemMeditationBinding>() {

    @EpoxyAttribute
    lateinit var item: MeditationEpoxyItem

    override fun getDefaultLayout() = R.layout.item_meditation

    override fun ItemMeditationBinding.bind() = with(item.meditation) {
        posterImageView.load(image.small) { transform(CenterCrop(), RoundedCorners(24)) }
        titleTextView.text = title
        subTitleTextView.text = subtitle
    }
}

data class MeditationEpoxyItem(val meditation: Meditation) : EpoxyItem
