package com.example.hotplacecontactapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotplacecontactapp.databinding.FragmentContactListBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
//            param2=it.getParcelable(ARG_PARAM2)!!
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

    /*
    코드의 가독성을 위해 initView() 라는 함수 안에 모든 기능을 함수 형태로 정의해서 정리해요~

    예시) setFloatingActionButton()

    private fun setFloatingActionButton() {
        binding.fabAddContact.setOnClickListener {
                ...
         }
    }
     */

    private fun initView() {
        val testList = mutableListOf<ContactData>()
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_lotteria), "0000000000000", "0000000000000", "0000000000000", "0000000000000", false))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_kfc), "1111111111111", "1111111111111", "1111111111111", "1111111111111", true))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_king), "2222222222222", "2222222222222", "2222222222222", "2222222222222", false))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_mcdonald), "3333333333333", "3333333333333", "3333333333333", "3333333333333", true))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_momstouch), "4444444444444", "4444444444444", "4444444444444", "4444444444444", false))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_mosburger), "5555555555555", "5555555555555", "5555555555555", "5555555555555", true))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_chicken_60), "6666666666666", "s6", "s6", "s6", false))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_chicken_bbq), "7777777777777", "s0", "s0", "s0", true))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_lotteria), "8888888888888", "s1", "s1", "s1", false))
        testList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/"+R.drawable.detail_burger_lotteria), "9999999999999", "s2", "s2", "s2", true))


        val adapter = Adapter(testList)
        binding.loRecyclerview.adapter = adapter
        binding.loRecyclerview.layoutManager = LinearLayoutManager(requireContext())

//        adapter.starClick=object : Adapter.StarClick{
//            override fun onClick(view: View, position: Int) {
//                var isfavorite=adapter.mItems[position].isFavorite==true
//
//                if(!isfavorite){
//
//                    adapter.mItems[position].isFavorite=true
//                    isfavorite=true
//                }
//
//            }
//        }


        adapter.itemClick=object : Adapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                val data = adapter.mItems[position]
                val fragment=ContactDetailFragment.newInstance(arrayListOf(data))
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.lo_fragmentLayout, fragment)
                    .addToBackStack(null)       //이전의 트랜잭션을 스택에 추가, 뒤로가기 누를시 이전의 프래그먼트로 돌아감
                    .commit()
                Log.d("ContactListFragment","data=$data")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ContactListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}