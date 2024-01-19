package com.example.hotplacecontactapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotplacecontactapp.adapter.Adapter
import com.example.hotplacecontactapp.data.ContactData
import com.example.hotplacecontactapp.data.ContactManager
import com.example.hotplacecontactapp.databinding.FragmentContactListBinding
import com.example.hotplacecontactapp.fragment.AddContactDialogFragment
import com.example.hotplacecontactapp.fragment.AddContactListener
import com.example.hotplacecontactapp.fragment.ContactDetailFragment
import com.example.hotplacecontactapp.fragment.FavoriteListFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ContactListFragment : Fragment(), AddContactListener {
    private var _binding: FragmentContactListBinding? = null
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
        adapter = Adapter(ContactManager.getList())
        binding.loRecyclerview.adapter = adapter
        binding.loRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        setAddContact()

        binding.tvFavoriteNum.text = ContactManager.contactList.count { it.isFavorite }.toString()

        adapter.starClick= object : Adapter.StarClick {
            override fun onClick(view: View, position: Int) {
                binding.tvFavoriteNum.text = ContactManager.contactList.count { it.isFavorite }.toString()
            }
        }

        //아이템 클릭-디테일페이지 전환
        adapter.itemClick = object : Adapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Log.d("ListFragment", "List clicked")
                val data = adapter.mItems[position]
                val fragmentToDetail = ContactDetailFragment.newInstance(arrayListOf(data))
                requireActivity().supportFragmentManager.beginTransaction()     //트랜잭션 시작
                    .replace(R.id.lo_fragmentLayout, fragmentToDetail)
                    .addToBackStack(null)       //이전의 트랜잭션을 스택에 추가, 뒤로가기 누를시 이전의 프래그먼트로 돌아감
                    .commit()
                Log.d("ListFragment", "data=$data")
            }
        }

        //아이템 롱클릭 - 아이템 삭제
        adapter.itemLongClick = object : Adapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                Log.d("ListFragment", "List LongClicked")
                val ad = AlertDialog.Builder(requireContext())
                ad.setIcon(R.drawable.ic_launcher_foreground)
                ad.setTitle("목록 삭제")
                ad.setMessage("목록을 정말로 삭제하시겠습니까?")
                ad.setPositiveButton("확인") { dialog, _ ->
                    Log.d("ListFragment", "position=$position")
                    ContactManager.contactList.removeAt(position)
                    Log.d("ListFragment", "List Removed")
                    adapter.notifyDataSetChanged()
                    binding.tvFavoriteNum.text=ContactManager.contactList.count{it.isFavorite}.toString()
                }
                ad.setNegativeButton("취소", null)
                ad.show()

            }
        }

        //즐겨찾기 클릭 - 목록 전환
        binding.loFavoriteLayout.setOnClickListener {
            val favoriteList: MutableList<ContactData> = ContactManager.contactList.filter { it.isFavorite }.toMutableList()
            val fragmentToFavorite= FavoriteListFragment.newInstance(favoriteList)
            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.lo_recyclerview,fragmentToFavorite)
                .replace(R.id.lo_fragmentLayout,fragmentToFavorite)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setAddContact() {
        binding.fabAddContact.setOnClickListener {
            val addContactDialog = AddContactDialogFragment()
            addContactDialog.listener = this
            addContactDialog.show(requireActivity().supportFragmentManager, "AddContactDialog")
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

    override fun onContactAdded() {
    adapter.notifyDataSetChanged()
    }
}