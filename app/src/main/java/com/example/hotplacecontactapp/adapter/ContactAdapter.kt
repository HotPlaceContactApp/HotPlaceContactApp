package com.example.hotplacecontactapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotplacecontactapp.data.ContactData
import com.example.hotplacecontactapp.R
import com.example.hotplacecontactapp.databinding.ItemRecyclerViewGridBinding
import com.example.hotplacecontactapp.databinding.ItemRecyclerViewListBinding


class ContactAdapter(var viewType: Int) :
    ListAdapter<ContactData, RecyclerView.ViewHolder>(diffUtil) {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null
    private var isFavorite = false

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ContactData>() {
            override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
                return oldItem == newItem
            }
        }

        const val TYPE_LIST = 0
        const val TYPE_GRID = 1
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LIST -> ListViewHolder(
                ItemRecyclerViewListBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            TYPE_GRID -> GridViewHolder(
                ItemRecyclerViewGridBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_LIST -> (holder as ListViewHolder).bind(getItem(position))
            TYPE_GRID -> (holder as GridViewHolder).bind(getItem(position))
        }
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }
    }

    inner class ListViewHolder(private val binding: ItemRecyclerViewListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ContactData) {
            with(binding) {
                ivListProfile.setImageURI(contact.profileImage)
                tvListName.text = contact.name
                ivStar.setImageResource(
                    if (contact.isFavorite) R.drawable.ic_yellow_star else R.drawable.ic_empty_star
                )
                ivStar.setOnClickListener {
                    isFavorite = if (!isFavorite) {
                        ivStar.setImageResource(R.drawable.ic_yellow_star)
                        true
                    } else {
                        ivStar.setImageResource(R.drawable.ic_empty_star)
                        false
                    }
                }
            }
        }
    }

    inner class GridViewHolder(private val binding: ItemRecyclerViewGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ContactData) {
            with(binding) {
                ivGridProfile.setImageURI(contact.profileImage)
                tvGridName.text = contact.name
            }
        }
    }
}