package com.example.hotplacecontactapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
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
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "0000000000000", "s0", "s0", "s0", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "1111111111111", "s1", "s1", "s1", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "2222222222222", "s2", "s2", "s2", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "3333333333333", "s3", "s3", "s3", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "4444444444444", "s4", "s4", "s4", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "5555555555555", "s5", "s5", "s5", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "6666666666666", "s6", "s6", "s6", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "7777777777777", "s0", "s0", "s0", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "8888888888888", "s1", "s1", "s1", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "9999999999999", "s2", "s2", "s2", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "aaaaaaaaaaaaa", "s3", "s3", "s3", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "bbbbbbbbbbbbb", "s4", "s4", "s4", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "ccccccccccccc", "s5", "s5", "s5", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "ddddddddddddd", "s6", "s6", "s6", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "eeeeeeeeeeeee", "s7", "s7", "s7", true))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "fffffffffffff", "s8", "s8", "s8", false))
        testList.add(ContactData(R.drawable.detail_burger_lotteria, "ggggggggggggg", "s9", "s9", "s9", true))

        val adapter = Adapter(testList)
        binding.loRecyclerview.adapter = adapter
        binding.loRecyclerview.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}