package com.example.hotplacecontactapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hotplacecontactapp.databinding.ActivityEditPageBinding

class EditPageActivity: AppCompatActivity() {
    private val binding by lazy { ActivityEditPageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        closeBtncheck()
        saveBtncheck()
//        addImgBtncheck()
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
                intent.putExtra("test", binding.textName.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun addImgBtncheck() {
        TODO("Not yet implemented")
    }
}