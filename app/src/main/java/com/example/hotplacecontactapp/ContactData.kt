package com.example.hotplacecontactapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactData(
    var profileImage : Int,
    var name : String,
    var phoneNumber : String,
    var address : String,
    var instaAddress : String,
    var isFavorite : Boolean
) : Parcelable
