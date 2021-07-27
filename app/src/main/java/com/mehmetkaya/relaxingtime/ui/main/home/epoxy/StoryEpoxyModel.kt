package com.mehmetkaya.relaxingtime.ui.main.home.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.data.remote.home.Story
import com.mehmetkaya.relaxingtime.databinding.ItemStoryBinding
import com.mehmetkaya.utils.epoxy.EpoxyItem
import com.mehmetkaya.utils.epoxy.ViewBindingEpoxyModel
import com.mehmetkaya.utils.exts.load

@EpoxyModelClass
abstract class StoryEpoxyModel : ViewBindingEpoxyModel<ItemStoryBinding>() {

    @EpoxyAttribute
    lateinit var item: StoryEpoxyItem

    override fun getDefaultLayout() = R.layout.item_story

    override fun ItemStoryBinding.bind() = with(item.story) {
        posterImageView.load(image.small) { transform(CenterCrop(), RoundedCorners(24)) }
        nameTextView.text = name
        categoryTextView.text = category
    }
}

data class StoryEpoxyItem(val story: Story) : EpoxyItem
