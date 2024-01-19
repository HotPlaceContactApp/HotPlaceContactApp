package com.example.hotplacecontactapp.fragment


import android.net.Uri
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotplacecontactapp.R
import com.example.hotplacecontactapp.adapter.ContactAdapter
import com.example.hotplacecontactapp.data.ContactData
import com.example.hotplacecontactapp.data.ContactManager
import com.example.hotplacecontactapp.databinding.FragmentContactListBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class ContactListFragment : Fragment(), AddContactListener {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val contactAdapter: ContactAdapter by lazy { ContactAdapter(ContactAdapter.TYPE_LIST) }

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
        setContactAdapter()
        showViewTypeDialog()
        setItemTouchHelper()
        setAddContact()
        countFavorite()
    }

    private fun setContactAdapter() {
        binding.loRecyclerview.apply {
            binding.tvFavoriteNum.text = ContactManager.contactList.count { it.isFavorite }.toString()
            adapter = contactAdapter.also {
                it.itemClick = object : ContactAdapter.ItemClick {
                    override fun onClick(view: View, position: Int) {
                        Log.d("ListFragment", "List clicked")
                        val data = contactAdapter.currentList[position]
                        val fragmentToDetail = ContactDetailFragment.newInstance(arrayListOf(data))
                        requireActivity().supportFragmentManager.beginTransaction()     //트랜잭션
                            .replace(R.id.main_view_layout, fragmentToDetail)
                            .addToBackStack(null)       //이전의 트랜잭션을 스택에 추가, 뒤로가기 누를시 이전의 프래그먼트로 돌아감
                            .commit()
                        Log.d("ListFragment", "data=$data")
                    }
                }

                it.itemLongClick = object : ContactAdapter.ItemLongClick {
                    override fun onLongClick(view: View, position: Int) {
                        Log.d("ListFragment", "List LongClicked")
                        val ad = AlertDialog.Builder(requireContext())
                        ad.setIcon(R.drawable.ic_launcher_foreground)
                        ad.setTitle("목록 삭제")
                        ad.setMessage("목록을 정말로 삭제하시겠습니까?")
                        ad.setPositiveButton("확인") { dialog, _ ->
                            Log.d("ListFragment", "position=$position")
                            ContactManager.removeContactData(position)
                            Log.d("ListFragment", "List Removed")
                            contactAdapter.submitList(ContactManager.getList())
                            contactAdapter.notifyItemRemoved(position)
                        }
                        ad.setNegativeButton("취소", null)
                        ad.show()
                    }
                }
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
        contactAdapter.submitList(ContactManager.getList())
        binding.loFavoriteLayout.setOnClickListener {
            val fragmentToFavorite= FavoriteListFragment.newInstance("favoriteList")
            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.lo_recyclerview,fragmentToFavorite)
                .replace(R.id.lo_fragmentLayout,fragmentToFavorite)
                .addToBackStack(null)
                .commit()
        }

    }

    private fun showViewTypeDialog() {
        binding.ivViewTypeChanger.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("뷰 타입 선택")
                .setMessage("원하는 뷰 타입을 선택해주세요.")
                .setPositiveButton("Grid View Type") { dialog, _ ->
                    changeAdapterViewType(ContactAdapter.TYPE_GRID, 2)
                }
                .setNegativeButton("List View Type") { dialog, _ ->
                    changeAdapterViewType(ContactAdapter.TYPE_LIST)
                }.show()
        }
    }

    private fun changeAdapterViewType(adapterType: Int, spanCount: Int = 1) {
        binding.loRecyclerview.apply {
            layoutManager = if (adapterType == ContactAdapter.TYPE_GRID) {
                GridLayoutManager(requireContext(), spanCount)
            } else {
                LinearLayoutManager(requireContext())
            }
            contactAdapter.viewType = adapterType
        }
    }

    private fun setItemTouchHelper() {
        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val contact = contactAdapter.currentList[position]

                    // 여기에서 전화 거는 동작을 수행하도록 구현
                    makePhoneCall(contact.phoneNumber)
                    contactAdapter.notifyItemChanged(viewHolder.adapterPosition)

                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    val icon: Bitmap
                    // actionState가 SWIPE 동작일 때 배경을 빨간색으로 칠하는 작업을 수행하도록 함
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        val itemView = viewHolder.itemView
                        val height = (itemView.bottom - itemView.top).toFloat()
                        val width = height / 4
                        val paint = Paint()
                        if (dX > 0) {
                            paint.color = Color.parseColor("#B28CEA8C")
                            val background = RectF(
                                itemView.left.toFloat() + dX,
                                itemView.top.toFloat(),
                                itemView.left.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            c.drawRect(background, paint)

                            icon = BitmapFactory.decodeResource(resources, R.drawable.ic_call)
                            val iconTop = itemView.top.toFloat() + (height - width) / 2
                            val iconLeft = itemView.left.toFloat() + width
                            val iconDst = RectF(
                                iconLeft,
                                iconTop,
                                iconLeft + width,
                                iconTop + width
                            )
                            c.drawBitmap(icon, null, iconDst, null)
                        }
                    }

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }


            })
        itemTouchHelper.attachToRecyclerView(binding.loRecyclerview)
    }

    private fun makePhoneCall(phoneNumber: String) {
        // 전화 거는 Intent를 생성
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))

        // 암시적 Intent로 전화 거는 앱 실행
        startActivity(intent)
    }

    private fun setItemClick() {
        contactAdapter.itemClick = object : ContactAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Log.d("ListFragment", "List clicked")
                val data = contactAdapter.currentList[position]
                val fragmentToDetail = ContactDetailFragment.newInstance(arrayListOf(data))
                requireActivity().supportFragmentManager.beginTransaction()     //트랜잭션
                    .replace(R.id.lo_fragmentLayout, fragmentToDetail)
                    .addToBackStack(null)       //이전의 트랜잭션을 스택에 추가, 뒤로가기 누를시 이전의 프래그먼트로 돌아감
                    .commit()
                Log.d("ListFragment", "data=$data")
            }
        }
    }

    private fun setItemLongClick() {
        contactAdapter.itemLongClick = object : ContactAdapter.ItemLongClick {
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
                    contactAdapter.notifyDataSetChanged()
                }
                ad.setNegativeButton("취소", null)
                ad.show()
            }
        }
    }

    private fun setAddContact() {
        binding.cardContactListPlus.setOnClickListener {
            val addContactDialog = AddContactDialogFragment()
            addContactDialog.listener = this
            addContactDialog.show(requireActivity().supportFragmentManager, "AddContactDialog")
        }
    }

    private fun countFavorite(){
        binding.tvFavoriteNum.text = ContactManager.contactList.count { it.isFavorite }.toString()
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
        contactAdapter.notifyDataSetChanged()
    }
}