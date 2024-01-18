package com.example.hotplacecontactapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hotplacecontactapp.databinding.FragmentMyPageBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            binding.textName.text=data?.getStringExtra("test")
        }
    }

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
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
//        updateProfileData()
//        checkPfp()
//        addData()
//        checkVisablity()

        binding.textEdit.setOnClickListener {
            val intent = Intent(activity, EditPageActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.cardCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${binding.textPhoneNumber.text}"))
            startActivity(intent)
        }
        binding.cardMessage.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:${binding.textPhoneNumber.text}"))
            startActivity(intent)
        }
    }

    private fun updateProfileData() {
        TODO("Not yet implemented")
    }

    private fun checkPfp() {
        TODO("Not yet implemented")
    }

    private fun addData() {
        TODO("Not yet implemented")
    }

    private fun checkVisablity() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}