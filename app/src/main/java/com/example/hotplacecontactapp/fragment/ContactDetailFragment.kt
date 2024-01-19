package com.example.hotplacecontactapp.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import com.example.hotplacecontactapp.MainActivity
import com.example.hotplacecontactapp.R
import com.example.hotplacecontactapp.data.ContactData
import com.example.hotplacecontactapp.databinding.ActivityContactDetailFragmentBinding


//private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ContactDetailFragment : Fragment() {

    private var _binding: ActivityContactDetailFragmentBinding? = null
    private val binding get() = _binding!!

    //    private var param1: String? = null
    private var param2: ArrayList<ContactData> = ArrayList()
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
            param2 = it.getParcelableArrayList(ARG_PARAM2)!!
//            param2 = it.getString(ARG_PARAM2)
            Log.d("DetailFragment", "param2=$param2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityContactDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val contactList: MutableList<ContactData> = mutableListOf()
//        val position= param2?.toInt()
        Log.d("DetailFragment", "initView() param2=$param2")

        binding.igDetailBackbutton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        val img = binding.ivDetailProfile
        val name = binding.tvDetailName
        val num = binding.tvDetailPhonenumber
        val address = binding.tvDetailAddress
        val instaId = binding.tvDetailInstarUri


        img.setImageURI(param2[0].profileImage)
        name.text = param2[0].name
        num.text = param2[0].phoneNumber
        instaId.text = param2[0].instaAddress
        address.text = param2[0].address

        val handler = Handler(Looper.getMainLooper())
        binding.addContactBtn5m.setOnClickListener {
            handler.postDelayed({ eventNotification() }, 1000)
        }
        binding.addContactBtn30m.setOnClickListener {
            handler.postDelayed({ eventNotification() }, 3000)
        }
        binding.addContactBtn1h.setOnClickListener {
            handler.postDelayed({ eventNotification() }, 5000)
        }
        
    }

    companion object {
        @JvmStatic
        fun newInstance(param2: ArrayList<ContactData>) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM2, param2)
                }
            }
    }

    fun eventNotification() {
        val manager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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