package com.example.hotplacecontactapp.fragment

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
import com.example.hotplacecontactapp.EditPageActivity
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
            var phonelist= listOf<String>(data!!.getStringExtra("phone1")!!, data.getStringExtra("phone2")!!, data.getStringExtra("phone3")!!, data.getStringExtra("phone4")!!)
            imgUri=Uri.parse(data.getStringExtra("img"))
            binding.imgPfp.setImageURI(imgUri)
            binding.textName.text= data.getStringExtra("name")
            binding.textPhoneNumber.text= data.getStringExtra("phone")

            phonelist=phonelist.filter { it.isNotEmpty() }
            repeat(4-phonelist.size) {
                phonelist=phonelist+""
            }

            binding.textPhoneNumber1.text=phonelist[0]
            if(phonelist[0].isEmpty().not()){
                binding.linlayPhone1.visibility= VISIBLE
            }
            else{
                binding.linlayPhone1.visibility= GONE
            }
            binding.textPhoneNumber2.text=phonelist[1]
            if(phonelist[1].isEmpty().not()){
                binding.linlayPhone2.visibility= VISIBLE
            }
            else{
                binding.linlayPhone2.visibility= GONE
            }
            binding.textPhoneNumber3.text=phonelist[2]
            if(phonelist[2].isEmpty().not()){
                binding.linlayPhone3.visibility= VISIBLE
            }
            else{
                binding.linlayPhone3.visibility= GONE
            }
            binding.textPhoneNumber4.text=phonelist[3]
            if(phonelist[3].isEmpty().not()){
                binding.linlayPhone4.visibility= VISIBLE
            }
            else{
                binding.linlayPhone4.visibility= GONE
            }
            binding.textEmail.text= data.getStringExtra("email")
            if(data.getStringExtra("email").isNullOrEmpty().not()){
                binding.linlayEmail.visibility= VISIBLE
            }
            else{
                binding.linlayEmail.visibility= GONE
            }
            binding.textInsta.text= data.getStringExtra("insta")
            if(data.getStringExtra("insta").isNullOrEmpty().not()){
                binding.linlayInsta.visibility= VISIBLE
            }
            else{
                binding.linlayInsta.visibility= GONE
            }
            binding.textWebsite.text= data.getStringExtra("website")
            if(data.getStringExtra("website").isNullOrEmpty().not()){
                binding.linlayWebsite.visibility= VISIBLE
            }
            else{
                binding.linlayWebsite.visibility= GONE
            }
            binding.textMemo.text= data.getStringExtra("memo")
            if(data.getStringExtra("memo").isNullOrEmpty().not()){
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
    ): View {
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
            intent.putExtra("phone1", binding.textPhoneNumber1.text.toString())
            intent.putExtra("phone2", binding.textPhoneNumber2.text.toString())
            intent.putExtra("phone3", binding.textPhoneNumber3.text.toString())
            intent.putExtra("phone4", binding.textPhoneNumber4.text.toString())
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
        binding.linlayPhone1.visibility=GONE
        binding.linlayPhone2.visibility=GONE
        binding.linlayPhone3.visibility=GONE
        binding.linlayPhone4.visibility=GONE
        binding.linlayEmail.visibility=GONE
        binding.linlayInsta.visibility=GONE
        binding.linlayWebsite.visibility=GONE
        binding.linlayMemo.visibility=GONE
    }

    private fun checkPfp() {
        if((imgUri?.equals(null) != false)or((imgUri.toString() == "null"))){
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
}