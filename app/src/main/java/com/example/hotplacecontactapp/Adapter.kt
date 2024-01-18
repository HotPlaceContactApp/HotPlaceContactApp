package com.example.hotplacecontactapp

import android.util.Log
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

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    interface StarClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null
    var starClick: StarClick? = null
    private var isFavorite = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerViewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        holder.iconImageView.setImageURI(mItems[position].profileImage)
        holder.name.text = mItems[position].name

        isFavorite = mItems[position].isFavorite
        Log.d("Adapter", "onbindViewholder position = $position")
        Log.d("Adapter", "OnbindViewholder isfavortie = $isFavorite")
        holder.star.setImageResource(
            if (isFavorite) {
                R.drawable.ic_yellow_star
            } else {
                R.drawable.ic_empty_star
            }
        )

//        holder.star.setOnClickListener {
//            starClick?.onClick(it,position)
//        }

        holder.star.setOnClickListener {
            Log.d("Adapter", "Star clicked")
            Log.d("Adapter", "position=$position")
            Log.d("Adapter", "isfavortie=$isFavorite")
            isFavorite = mItems[position].isFavorite
            if (!isFavorite) {
                holder.star.setImageResource(R.drawable.ic_yellow_star)
                mItems[position].isFavorite = true
            } else {
                holder.star.setImageResource(R.drawable.ic_empty_star)
                mItems[position].isFavorite = false
            }
            notifyItemChanged(position)
        }
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
        val star = binding.ivStar
    }
}