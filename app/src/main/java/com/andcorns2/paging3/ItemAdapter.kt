package com.andcorns2.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andcorns2.paging3.databinding.ListItemBinding

class ItemAdapter : PagingDataAdapter<ItemData, ItemAdapter.ItemViewHolder>(ARTICLE_DIFF_CALLBACK) {
    interface CustomListenerInterface {
        fun removeListener(position: Int, sampleData: ItemData)
    }

    inner class ItemViewHolder(private val mBinding : ListItemBinding): RecyclerView.ViewHolder(mBinding.root) {
        fun bind(listData: ItemData) {
            mBinding.tvItem.text = listData.title.toString()+"번 아이템"
            mBinding.tvItem.setOnClickListener {
                onRemoveListener?.removeListener(bindingAdapterPosition, listData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }

    private var onRemoveListener: CustomListenerInterface? = null

    fun removeListener(pOnClick: CustomListenerInterface) {
        this.onRemoveListener = pOnClick
    }

    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemData>() {
            override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                oldItem == newItem
        }
    }
}