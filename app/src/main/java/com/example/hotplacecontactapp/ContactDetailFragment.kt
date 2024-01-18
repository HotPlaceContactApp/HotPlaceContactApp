package com.example.hotplacecontactapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotplacecontactapp.databinding.ActivityContactDetailFragmentBinding
import com.example.hotplacecontactapp.databinding.FragmentMyPageBinding


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ContactDetailFragment : Fragment() {

    private var _binding: ActivityContactDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private var param1: ArrayList<ContactData> = ArrayList()
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelableArrayList(ARG_PARAM1)!!
            param2 = it.getString(ARG_PARAM2)
            Log.d("detail", "${param1}")
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

        val contactList:MutableList<ContactData> = mutableListOf()

        val img = param1[0].profileImage
        val phone = param1[0].phoneNumber
        val insta = param1[0].instaAddress
        val address = param1[0].address
        val name = param1[0].name

        binding.igDetailProfile.setImageResource(img)
        binding.tvDetailPhonenumber.setText(phone)
        binding.tvDetailInstarUri.setText(insta)
        binding.tvDetailAddress.setText(address)
        binding.tvDetailName.setText(name)



        Log.d("detail", "onViewCreated")



//        프레그먼트 뒤로가기
        binding.igDetailBackbutton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()

            Log.d("detail", "onCreateView")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<ContactData>) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                }
            }
    }
}