package com.example.hotplacecontactapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hotplacecontactapp.databinding.ActivityEditPageBinding

class EditPageActivity: AppCompatActivity() {
    private val binding by lazy { ActivityEditPageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}