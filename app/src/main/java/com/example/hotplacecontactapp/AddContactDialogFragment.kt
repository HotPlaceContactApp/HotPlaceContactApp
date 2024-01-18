package com.example.hotplacecontactapp

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
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

            val handler = Handler(Looper.getMainLooper())
            addContactBtnNotification.setOnClickListener {
                handler.postDelayed({ eventNotification() }, 3000)
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

    fun eventNotification() {
        val manager = activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        val channelId = "one-channel"
        val channelName = "My Channel One"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "My Channel One Description"
            setShowBadge(true)
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            setSound(uri, audioAttributes)
            enableVibration(true)
        }

        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(requireContext(), channelId)

        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("연락처 알림")
            setContentText("설정한 알림이 도착했습니다!!")
            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }
        manager.notify(11, builder.build())
    }

}

interface AddContactListener {
    fun onContactAdded()
}

