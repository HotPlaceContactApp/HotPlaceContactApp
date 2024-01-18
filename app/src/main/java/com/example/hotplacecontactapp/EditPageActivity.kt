package com.example.hotplacecontactapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
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
        closeBtncheck()
        saveBtncheck()
        addImgBtncheck()
    }

    private fun initView() {
        binding.imgPfp.setImageURI(Uri.parse(intent.getStringExtra("img")))
        binding.textName.setText(intent.getStringExtra("name"))
        binding.textPhoneNumber.setText(intent.getStringExtra("phone"))
        binding.textEmail.setText(intent.getStringExtra("email"))
        binding.textInsta.setText(intent.getStringExtra("insta"))
        binding.textWebsite.setText(intent.getStringExtra("website"))
        binding.textMemo.setText(intent.getStringExtra("memeo"))
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
                var intent = Intent(this, EditPageActivity::class.java)
                intent.putExtra("img", imgUri.toString())
                intent.putExtra("name", binding.textName.text.toString())
                intent.putExtra("phone", binding.textPhoneNumber.text.toString())
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