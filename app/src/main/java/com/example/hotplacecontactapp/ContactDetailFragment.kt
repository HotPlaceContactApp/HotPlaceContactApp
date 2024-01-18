package com.example.hotplacecontactapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotplacecontactapp.databinding.ActivityContactDetailFragmentBinding


//private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ContactDetailFragment : Fragment() {

    private var _binding: ActivityContactDetailFragmentBinding? = null

    private val binding get() = _binding!!

//    private var param1: String? = null
    private var param2: ArrayList<ContactData> = ArrayList()
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
            param2=it.getParcelableArrayList(ARG_PARAM2)!!
//            param2 = it.getString(ARG_PARAM2)
            Log.d("DetailFragment","param2=$param2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityContactDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        val contactList: MutableList<ContactData> = mutableListOf()
//        val position= param2?.toInt()
        Log.d("DetailFragment","initView() param2=$param2")

        val img = binding.ivRestaurantLogo
        val name=binding.tvDetailName
        val num=binding.tvDetailPhonenumber
        val address=binding.tvDetailAddress
        val instaId=binding.tvDetailInstarAddress



        img.setImageURI(param2[0].profileImage)
        name.text = param2[0].name
        num.text = param2[0].phoneNumber
        instaId.text = param2[0].instaAddress
        address.text=param2[0].address


    }

    companion object {
        @JvmStatic
        fun newInstance(param2: ArrayList<ContactData>) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM2, param2)
                }
            }
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String) =
//            ContactDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}