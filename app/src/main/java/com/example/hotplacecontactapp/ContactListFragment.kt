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
        Log.d("ContactListFragment", "check0")
        val testlist= mutableListOf<ContactData>()
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"0000000000000","s0","s0","s0",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"111111111111","s1","s1","s1",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"22222222222222","s2","s2","s2",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"333333333333","s3","s3","s3",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"444444444444","s4","s4","s4",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"5555555555","s5","s5","s5",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"66666666666666","s6","s6","s6",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"777777777777777","s0","s0","s0",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"8888888888888","s1","s1","s1",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"9999999999999","s2","s2","s2",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"aaaaaaaaaaaaa","s3","s3","s3",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"bbbbbbbbbbbbb","s4","s4","s4",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"cccccccccc","s5","s5","s5",true))
        testlist.add(ContactData(R.drawable.ic_launcher_foreground,"dddddddddddddddd","s6","s6","s6",true))

        val adapter=Adapter(testlist)
        binding.loRecyclerview.adapter=adapter
        binding.loRecyclerview.layoutManager=LinearLayoutManager(requireContext())


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