package com.example.hotplacecontactapp

import android.net.Uri

object ContactManager {
    private val contactList: MutableList<ContactData> = mutableListOf()

    init {
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_lotteria), "0000000000000", "s0", "s0", "s0", true))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_lotteria), "1111111111111", "s1", "s1", "s1", false))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_lotteria), "2222222222222", "s2", "s2", "s2", true))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_lotteria), "3333333333333", "s3", "s3", "s3", false))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "4444444444444", "s4", "s4", "s4", true))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "5555555555555", "s5", "s5", "s5", false))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "6666666666666", "s6", "s6", "s6", true))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "7777777777777", "s0", "s0", "s0", false))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "8888888888888", "s1", "s1", "s1", true))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "9999999999999", "s2", "s2", "s2", false))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "aaaaaaaaaaaaa", "s3", "s3", "s3", true))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "bbbbbbbbbbbbb", "s4", "s4", "s4", false))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "ccccccccccccc", "s5", "s5", "s5", true))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "ddddddddddddd", "s6", "s6", "s6", false))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "eeeeeeeeeeeee", "s7", "s7", "s7", true))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "fffffffffffff", "s8", "s8", "s8", false))
//        testList.add(ContactData(R.drawable.detail_burger_lotteria, "ggggggggggggg", "s9", "s9", "s9", true))
    }

    fun addContactData(data: ContactData) {
        contactList.add(data)
    }

    fun getList(): MutableList<ContactData> {
        return contactList
    }

}