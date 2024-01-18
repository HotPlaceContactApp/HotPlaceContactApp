package com.example.hotplacecontactapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.hotplacecontactapp.databinding.AddcontactLayoutDialogBinding

class AddContactDialogFragment : DialogFragment() {
    private lateinit var imageLauncher: ActivityResultLauncher<Intent>
    private var profileImage: Uri? = null
    private var _binding: AddcontactLayoutDialogBinding? = null
    private val binding get() = _binding!!
    var listener: AddContactListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AddcontactLayoutDialogBinding.inflate(inflater, container, false)

        imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val img_URI = result.data?.data
                binding.addContactIvStorePhoto.setImageURI(img_URI)
                profileImage = img_URI
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            addContactIvStorePhoto.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                imageLauncher.launch(intent)
            }

            addContactBtnPositive.setOnClickListener {

                addContactTvPhotoError.text = setMessageValidPhoto()
                addContactTvNameError.text = setMessageValidName()
                addContactTvPhoneError.text = setMessageValidPhone()
                addContactTvAddressError.text = setMessageValidAddress()
                addContactTvInstaError.text = setMessageValidInsta()

                val addData = ContactData(
                    profileImage = profileImage,
                    name = addContactEtStoreName.text.toString(),
                    phoneNumber = addContactEtStorePhone.text.toString(),
                    address = addContactEtStoreAddress.text.toString(),
                    instaAddress = addContactEtStoreInsta.text.toString(),
                    isFavorite = false
                )

                validAdd(addData)
            }

            addContactBtnNegative.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setMessageValidPhoto(): String = if (profileImage == null) {
        getString(R.string.valid_photo)
    } else getString(R.string.valid_pass)

    private fun setMessageValidName(): String = if (binding.addContactEtStoreName.text.toString().isBlank()) {
        getString(R.string.valid_name)
    } else getString(R.string.valid_pass)

    private fun setMessageValidPhone(): String {
        val phone = binding.addContactEtStorePhone.text.toString()
        return if (phone.isBlank()) {
            getString(R.string.valid_phone)
        } else if (!validatePhoneNumber(phone)) {
            getString(R.string.valid_phoneFormat)
        } else getString(R.string.valid_pass)
    }

    private fun setMessageValidAddress(): String = if (binding.addContactEtStoreAddress.text.toString().isBlank()) {
        getString(R.string.valid_address)
    } else getString(R.string.valid_pass)

    private fun setMessageValidInsta(): String {
        val instaID = binding.addContactEtStoreInsta.text.toString()
        return if (instaID.isBlank()) {
            getString(R.string.valid_insta)
        } else if (!validateInstaID(instaID)) {
            getString(R.string.valid_instaFormat)
        } else getString(R.string.valid_pass)
    }

    private fun validatePhoneNumber(phone: String): Boolean {
        val regex = Regex("^([0-9]{2,4})-\\d{3,4}-\\d{4}\$")
        return regex.matches(phone)
    }

    private fun validateInstaID(instaID: String): Boolean {
        val regex = Regex("""^[A-Za-z0-9._]+$""")
        return regex.matches(instaID)
    }

    private fun validAdd(contactData: ContactData) {
        if (setMessageValidPhone().isBlank() && setMessageValidName().isBlank() && setMessageValidPhone().isBlank() && setMessageValidAddress().isBlank() && setMessageValidInsta().isBlank()) {
            ContactManager.addContactData(contactData)
            listener?.onContactAdded()
            dismiss()
        }
    }

}

interface AddContactListener {
    fun onContactAdded()
}

