package com.example.hotplacecontactapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotplacecontactapp.databinding.FragmentFavoriteListBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FavoriteListFragment : Fragment() {


    private var _binding: FragmentFavoriteListBinding?=null
    private var isFavorite = false

    private val binding get() = _binding!!

    private var param1: ArrayList<ContactData> = ArrayList()
    private var param2: String? = null
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelableArrayList(ARG_PARAM1)!!
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
        val recData=param1
        Log.d("FavoriteFragment", "recData=$recData")
        adapter= Adapter(recData)
        binding.checkRecyclerView.adapter=adapter
        binding.checkRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        Log.d("FavoriteFragment", "Star Clicked")

//        isFavorite=recData[0].isFavorite
//        if(!isFavorite){
//
//        }else{
//
//        }

        adapter.starClick= object : Adapter.StarClick{
            override fun onClick(view: View, position: Int) {
                adapter.notifyItemChanged(position)
                isFavorite=recData[0].isFavorite
                if(!isFavorite){
                    recData[0].isFavorite=true
                }else{
                    recData[0].isFavorite=false
                }
                adapter.mItems.filter{it.name==recData[0].name}.forEach { it.isFavorite= recData[0].isFavorite}
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
        fun newInstance(param1: MutableList<ContactData>) =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, ArrayList(param1))
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}