package com.example.hotplacecontactapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotplacecontactapp.databinding.FragmentContactListBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FavoriteListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null

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
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val recData=param1
        adapter= Adapter(recData)
//        binding.

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: List<ContactData>) =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
//                    putParcelableArrayList(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}