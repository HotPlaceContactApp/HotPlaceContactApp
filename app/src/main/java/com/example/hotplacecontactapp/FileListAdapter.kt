package com.example.hotplacecontactapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FileListAdapter internal constructor(val context: Context, val fileList: ArrayList<String>?)
    : RecyclerView.Adapter<FileListAdapter.FileViewHolder>(), Filterable {

    private var files: ArrayList<String>? = fileList

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val str: TextView = itemView.an_str

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_list, parent, false)
        return FileViewHolder(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val current = files?.get(position)
//        holder.str.text = current
    }


    override fun getItemCount() = files?.size!!

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                files = if (charString.isEmpty()) {
                    fileList
                } else {
                    val filteredList = ArrayList<String>()
                    if (fileList != null) {
                        for (name in fileList) {
                            if(name.toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(name);
                            }
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = files
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                files  = results.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }


}
