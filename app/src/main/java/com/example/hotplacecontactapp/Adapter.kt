package com.example.hotplacecontactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotplacecontactapp.databinding.ItemRecyclerViewListBinding

//class Adapter:ListAdapter<ContactData, Adapter.Holder>(object : DiffUtil.ItemCallback<ContactData>() {
// }
class Adapter(val mItems: MutableList<ContactData>) : RecyclerView.Adapter<Adapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerViewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }
        holder.iconImageView.setImageResource(mItems[position].profileImage)
        holder.name.text = mItems[position].name
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemRecyclerViewListBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.ivListProfile
        val name = binding.tvListName
    }
}