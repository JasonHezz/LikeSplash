package com.github.jasonhezz.likesplash.ui.epoxy.model

import android.graphics.Typeface.BOLD
import android.text.style.StyleSpan
import androidx.core.text.buildSpannedString
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_search_hint.*

@EpoxyModelClass(layout = R.layout.item_search_hint)
abstract class SearchHintModel : EpoxyModelWithHolder<BaseViewHolder>() {
    @EpoxyAttribute
    var hint: String? = null

    @EpoxyAttribute
    var query: String = ""

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        hint?.let {
            val hintSpan = it.toSpannable()
            val start = hintSpan.indexOf(query, ignoreCase = true)
            hintSpan[start, start + query.length] = StyleSpan(BOLD)
            holder.hint.text = buildSpannedString {
                append(hintSpan)
            }
        }

    }
}