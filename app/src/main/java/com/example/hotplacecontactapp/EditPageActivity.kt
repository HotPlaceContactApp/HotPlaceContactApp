package com.example.hotplacecontactapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.isVisible
import com.example.hotplacecontactapp.databinding.ActivityEditPageBinding

class EditPageActivity: AppCompatActivity() {
    private val binding by lazy { ActivityEditPageBinding.inflate(layoutInflater) }

    private var imgUri: Uri? =null

    private val getImage=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK) {
            imgUri = it.data?.data
            grantUriPermission(
                "com.example.hotplacecontactapp",
                imgUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            binding.imgPfp.setImageURI(imgUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        addPhonenumber()
        removePhonenumber()
        closeBtncheck()
        saveBtncheck()
        addImgBtncheck()
    }

    private fun removePhonenumber() {
        binding.imgMinus1.setOnClickListener {
            val phonenumberList= mutableListOf<String>(binding.textPhoneNumber1.text.toString(), binding.textPhoneNumber2.text.toString(), binding.textPhoneNumber3.text.toString(), binding.textPhoneNumber4.text.toString())

            binding.linlayPhoneList.children.last { it.isVisible and (it is LinearLayout)}.visibility=GONE
            phonenumberList.removeAt(0)
            phonenumberList.add("")
            binding.textPhoneNumber1.setText(phonenumberList[0])
            binding.textPhoneNumber2.setText(phonenumberList[1])
            binding.textPhoneNumber3.setText(phonenumberList[2])
            binding.textPhoneNumber4.setText(phonenumberList[3])

            if(binding.linlayPhone4.visibility== GONE){
                binding.textAddPhonenumber.visibility= VISIBLE
            }
        }
        binding.imgMinus2.setOnClickListener {
            val phonenumberList= mutableListOf<String>(binding.textPhoneNumber1.text.toString(), binding.textPhoneNumber2.text.toString(), binding.textPhoneNumber3.text.toString(), binding.textPhoneNumber4.text.toString())

            binding.linlayPhoneList.children.last { it.isVisible and (it is LinearLayout)}.visibility=GONE
            phonenumberList.removeAt(1)
            phonenumberList.add("")
            binding.textPhoneNumber1.setText(phonenumberList[0])
            binding.textPhoneNumber2.setText(phonenumberList[1])
            binding.textPhoneNumber3.setText(phonenumberList[2])
            binding.textPhoneNumber4.setText(phonenumberList[3])

            if(binding.linlayPhone4.visibility== GONE){
                binding.textAddPhonenumber.visibility= VISIBLE
            }
        }
        binding.imgMinus3.setOnClickListener {
            val phonenumberList= mutableListOf<String>(binding.textPhoneNumber1.text.toString(), binding.textPhoneNumber2.text.toString(), binding.textPhoneNumber3.text.toString(), binding.textPhoneNumber4.text.toString())

            binding.linlayPhoneList.children.last { it.isVisible and (it is LinearLayout)}.visibility=GONE
            phonenumberList.removeAt(2)
            phonenumberList.add("")
            binding.textPhoneNumber1.setText(phonenumberList[0])
            binding.textPhoneNumber2.setText(phonenumberList[1])
            binding.textPhoneNumber3.setText(phonenumberList[2])
            binding.textPhoneNumber4.setText(phonenumberList[3])

            if(binding.linlayPhone4.visibility== GONE){
                binding.textAddPhonenumber.visibility= VISIBLE
            }
        }
        binding.imgMinus4.setOnClickListener {
            val phonenumberList= mutableListOf<String>(binding.textPhoneNumber1.text.toString(), binding.textPhoneNumber2.text.toString(), binding.textPhoneNumber3.text.toString(), binding.textPhoneNumber4.text.toString())

            binding.linlayPhoneList.children.last { it.isVisible and (it is LinearLayout)}.visibility=GONE
            phonenumberList.removeAt(3)
            phonenumberList.add("")
            binding.textPhoneNumber1.setText(phonenumberList[0])
            binding.textPhoneNumber2.setText(phonenumberList[1])
            binding.textPhoneNumber3.setText(phonenumberList[2])
            binding.textPhoneNumber4.setText(phonenumberList[3])

            if(binding.linlayPhone4.visibility== GONE){
                binding.textAddPhonenumber.visibility= VISIBLE
            }
        }
    }

    private fun addPhonenumber() {
        binding.textAddPhonenumber.setOnClickListener {
            for(child in binding.linlayPhoneList.children){
                if(child.visibility== GONE){
                    child.visibility= VISIBLE
                    break
                }
            }
            if(binding.linlayPhone4.visibility== VISIBLE){
                binding.textAddPhonenumber.visibility=GONE
            }
        }
    }

    private fun initView() {
        binding.imgPfp.setImageURI(Uri.parse(intent.getStringExtra("img")))
        binding.textName.setText(intent.getStringExtra("name"))
        binding.textPhoneNumber.setText(intent.getStringExtra("phone"))
        binding.textEmail.setText(intent.getStringExtra("email"))
        binding.textInsta.setText(intent.getStringExtra("insta"))
        binding.textWebsite.setText(intent.getStringExtra("website"))
        binding.textMemo.setText(intent.getStringExtra("memeo"))
        binding.textPhoneNumber1.setText(intent.getStringExtra("phone1"))
        if(intent.getStringExtra("phone1").isNullOrEmpty().not()){
            binding.linlayPhone1.visibility= VISIBLE
        }
        else{
            binding.linlayPhone1.visibility=GONE
        }
        binding.textPhoneNumber2.setText(intent.getStringExtra("phone2"))
        if(intent.getStringExtra("phone2").isNullOrEmpty().not()){
            binding.linlayPhone2.visibility= VISIBLE
        }
        else{
            binding.linlayPhone2.visibility=GONE
        }
        binding.textPhoneNumber3.setText(intent.getStringExtra("phone3"))
        if(intent.getStringExtra("phone3").isNullOrEmpty().not()){
            binding.linlayPhone3.visibility= VISIBLE
        }
        else{
            binding.linlayPhone3.visibility=GONE
        }
        binding.textPhoneNumber4.setText(intent.getStringExtra("phone4"))
        if(intent.getStringExtra("phone4").isNullOrEmpty().not()){
            binding.linlayPhone4.visibility= VISIBLE
        }
        else{
            binding.linlayPhone4.visibility=GONE
        }
        if(binding.linlayPhone4.visibility== VISIBLE){
            binding.textAddPhonenumber.visibility=GONE
        }
    }

    private fun closeBtncheck() {
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.textExitButton.setOnClickListener {
            finish()
        }
    }

    private fun saveBtncheck() {
        binding.textSaveButton.setOnClickListener {
            if (binding.textName.text.isEmpty()) {
                Toast.makeText(this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show()
            } else if (binding.textPhoneNumber.text.isEmpty()) {
                Toast.makeText(this, "휴대전화 번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, EditPageActivity::class.java)
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
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun addImgBtncheck() {
        binding.cardAddImg.setOnClickListener {
            val imgIntent=Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            getImage.launch(imgIntent)
        }
    }
}
