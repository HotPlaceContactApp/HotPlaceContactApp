package com.example.hotplacecontactapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotplacecontactapp.adapter.Adapter
import com.example.hotplacecontactapp.data.ContactData
import com.example.hotplacecontactapp.data.ContactManager
import com.example.hotplacecontactapp.databinding.FragmentContactListBinding
import com.example.hotplacecontactapp.fragment.AddContactDialogFragment
import com.example.hotplacecontactapp.fragment.AddContactListener
import com.example.hotplacecontactapp.fragment.ContactDetailFragment
import com.example.hotplacecontactapp.fragment.FavoriteListFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


