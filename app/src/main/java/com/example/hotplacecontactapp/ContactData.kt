package com.example.hotplacecontactapp

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactData(
    var profileImage: Uri?,
    var name: String,
    var phoneNumber: String,
    var address: String,
    var instaAddress: String,
    var isFavorite: Boolean
) : Parcelable
