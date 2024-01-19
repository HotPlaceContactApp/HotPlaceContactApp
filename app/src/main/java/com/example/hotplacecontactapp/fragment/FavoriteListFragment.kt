package com.example.hotplacecontactapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotplacecontactapp.adapter.Adapter
import com.example.hotplacecontactapp.adapter.ContactAdapter
import com.example.hotplacecontactapp.adapter.ContactAdapter.Companion.TYPE_LIST
import com.example.hotplacecontactapp.data.ContactData
import com.example.hotplacecontactapp.data.ContactManager
import com.example.hotplacecontactapp.databinding.FragmentFavoriteListBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FavoriteListFragment : Fragment() {


    private var _binding: FragmentFavoriteListBinding?=null
    private var isFavorite = false

    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val favoriteList: MutableList<ContactData> = ContactManager.contactList.filter { it.isFavorite }.toMutableList()
        Log.d("FavoriteFragment", "recData=$favoriteList")
        val contactAdapter= ContactAdapter(ContactAdapter.TYPE_LIST)
        binding.checkRecyclerView.adapter=contactAdapter
        binding.checkRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        Log.d("FavoriteFragment", "Star Clicked")


        contactAdapter.starClick= object : ContactAdapter.StarClick{
            override fun onClick(view: View, position: Int) {
                isFavorite=favoriteList[position].isFavorite
                favoriteList[position].isFavorite = !isFavorite
                ContactManager.contactList.filter{it.name==favoriteList[0].name}.forEach { it.isFavorite= favoriteList[0].isFavorite}
                adapter.notifyDataSetChanged()
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1:String) =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}