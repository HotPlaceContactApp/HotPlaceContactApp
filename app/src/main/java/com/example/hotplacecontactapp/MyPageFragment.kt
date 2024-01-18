package com.example.hotplacecontactapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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

    private var imgUri: Uri? = null
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            imgUri=Uri.parse(data?.getStringExtra("img"))
            binding.imgPfp.setImageURI(imgUri)
            binding.textName.text=data?.getStringExtra("name")
            binding.textPhoneNumber.text=data?.getStringExtra("phone")
            binding.textEmail.text=data?.getStringExtra("email")
            if(data?.getStringExtra("email").isNullOrEmpty().not()){
                binding.linlayEmail.visibility= VISIBLE
            }
            else{
                binding.linlayEmail.visibility= GONE
            }
            binding.textInsta.text=data?.getStringExtra("insta")
            if(data?.getStringExtra("insta").isNullOrEmpty().not()){
                binding.linlayInsta.visibility= VISIBLE
            }
            else{
                binding.linlayInsta.visibility= GONE
            }
            binding.textWebsite.text=data?.getStringExtra("website")
            if(data?.getStringExtra("website").isNullOrEmpty().not()){
                binding.linlayWebsite.visibility= VISIBLE
            }
            else{
                binding.linlayWebsite.visibility= GONE
            }
            binding.textMemo.text=data?.getStringExtra("memo")
            if(data?.getStringExtra("memo").isNullOrEmpty().not()){
                binding.linlayMemo.visibility= VISIBLE
            }
            else{
                binding.linlayMemo.visibility= GONE
            }
            checkPfp()
        }
    }

    private val defaultStringList : List<String> = listOf("홍길동", "010-1234-5678")

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
        initProfileData()
        checkPfp()

        binding.textEdit.setOnClickListener {
            val intent = Intent(activity, EditPageActivity::class.java)
            intent.putExtra("img", imgUri.toString())
            intent.putExtra("name", binding.textName.text.toString())
            intent.putExtra("phone", binding.textPhoneNumber.text.toString())
            intent.putExtra("email", binding.textEmail.text.toString())
            intent.putExtra("insta", binding.textInsta.text.toString())
            intent.putExtra("website", binding.textWebsite.text.toString())
            intent.putExtra("memo", binding.textMemo.text.toString())
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

    private fun initProfileData() {
        binding.imgPfp.setImageURI(imgUri)
        binding.textName.text=defaultStringList[0]
        binding.textPhoneNumber.text=defaultStringList[1]
        binding.linlayEmail.visibility=GONE
        binding.linlayInsta.visibility=GONE
        binding.linlayWebsite.visibility=GONE
        binding.linlayMemo.visibility=GONE
    }

    private fun checkPfp() {
        if(imgUri==null){
            binding.textPfp.text=binding.textName.text.first().toString()
        }
        else{
            binding.textPfp.text=""
        }
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