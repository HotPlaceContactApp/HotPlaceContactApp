package com.example.hotplacecontactapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "0000000000000", "s0", "s0", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_burger_kfc, "1111111111111", "s1", "s1", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_burger_mcdonald, "2222222222222", "s2", "s2", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_burger_king, "3333333333333", "s3", "s3", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_burger_momstouch, "4444444444444", "s4", "s4", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_burger_mosburger, "5555555555555", "s5", "s5", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_chicken_60, "6666666666666", "s6", "s6", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_chicken_bbq, "7777777777777", "s0", "s0", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_chicken_ddangddang, "8888888888888", "s1", "s1", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_chicken_goobne, "9999999999999", "s2", "s2", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_chicken_nene, "aaaaaaaaaaaaa", "s3", "s3", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_chicken_kyochon, "bbbbbbbbbbbbb", "s4", "s4", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_chicken_pericana, "ccccccccccccc", "s5", "s5", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_chicken_puradak, "ddddddddddddd", "s6", "s6", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_pizza_alvolo, "eeeeeeeeeeeee", "s7", "s7", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))
//        testList.add(ContactData(R.drawable.detail_pizza_banolim, "fffffffffffff", "s8", "s8", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", false))
//        testList.add(ContactData(R.drawable.detail_pizza_domino, "ggggggggggggg", "s9", "s9", "www.ajkldjfkljlklldfkladfk.dkfjlkajldkfjlksdlf/ajdsklfjakldjfklsjdflkja;ldkjflkjskldkdialktnld", true))




        val adapter = Adapter(testList)
        binding.loRecyclerview.adapter = adapter
        binding.loRecyclerview.layoutManager = LinearLayoutManager(requireContext())


        adapter.itemClick = object : Adapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val dataToSend = adapter.mItems[position]
                val fragment = ContactDetailFragment.newInstance(arrayListOf(dataToSend))
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_layout, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
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