package com.github.jasonhezz.likesplash.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item.*


/**
 * Created by JavaCoder on 2017/6/28.
 */
class ListFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val adpater = Adapter(generateItemModelList())
    rv.adapter = adpater
    refreshLayout.isEnabled = true
    refreshLayout.setOnRefreshListener {
      refreshLayout.postDelayed({
        adpater.itemModelList = generateItemModelList()
        adpater.notifyDataSetChanged()
        refreshLayout?.isRefreshing = false
      }, 3000)
    }
  }

  companion object {
    @JvmStatic
    fun newInstance() = ListFragment()

    fun generateItemModelList(): ArrayList<ItemModel> {
      val itemModelList = ArrayList<ItemModel>()
      var i = 0
      while (i < 20) {
        itemModelList.add(ItemModel(
            "Item #" + ++i,
            "Earth isn't flat"
        ))
      }

      return itemModelList
    }

    private fun loadMore(size: Int): ArrayList<ItemModel> {
      val itemModelList = ArrayList<ItemModel>()
      (size..(size + 10)).forEach {
        itemModelList.add(ItemModel(
            "Item # $it",
            "Earth isn't flat"
        ))
      }
      return itemModelList
    }
  }
}

class Adapter(var itemModelList: ArrayList<ItemModel>) : RecyclerView.Adapter<MyViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item, parent, false)
    return MyViewHolder(view)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind(itemModelList[position])
  }

  override fun getItemCount(): Int {
    return itemModelList.size
  }
}

class MyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(
    containerView), LayoutContainer {
  fun bind(item: ItemModel) {
    title.text = item.title
    summary.text = item.summary
  }
}

data class ItemModel(var title: String, var summary: String)