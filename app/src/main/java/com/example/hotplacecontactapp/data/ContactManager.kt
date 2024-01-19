package com.example.hotplacecontactapp.data

import android.net.Uri
import com.example.hotplacecontactapp.R

object ContactManager {
    val contactList: MutableList<ContactData> = mutableListOf()

    init {
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_kfc), "kfc 대한민국 서울특별시 종로구 청계천로 청계천점", "02-2564-8826", "서울 강남구 역삼동 16", "https://www.instagram.com/seouleating/", true))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_lotteria), "롯데리아", "02-4856-9645", "서울 강남구 논현동 59-9", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_king), "버거킹", "02-8544-2556", "서울 강남구 역삼동 133", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", true))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_mcdonald), "맥도날드", "02-1566-5593", "서울 강남구 논현동 1-11", "https://www.instagram.com/seouleating/", false))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_burger_momstouch), "맘스터치", "02-1515-1675", "서울 강남구 역삼동 35", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_60), "60계 치킨", "02-2513-5152",  "서울 강남구 논현동 126-5", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_bbq), "bbq", "02-5642-4516", "서울 강남구 역삼동 128-5", "https://www.instagram.com/seouleating/", false))
        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_ddangddang), "땅땅치킨", "02-5466-8921", "서울 강남구 논현동 11", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_goobne), "굽네치킨", "02-2651-2563", "서울 강남구 역삼동 123","https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_kyochon), "교촌치킨", "02-4661-5476", "서울 강남구 논현동 33", "https://www.instagram.com/seouleating/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_nene), "네네치킨", "02-4661-7631", "서울 강남구 역삼동 2", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_pericana), "페리카나치킨", "02-1643-5197",  "서울 강남구 역삼동 563", "https://www.instagram.com/seouleating/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_chicken_puradak), "푸라닥치킨", "02-1673-1498", "서울 강남구 논현동 12", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_pizza_alvolo), "피자 알볼로", "02-4761-4511", "서울 강남구 역삼동 56", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_pizza_banolim), "반올림 피자", "02-5461-2116", "서울 강남구 역삼동 6-9", "https://www.instagram.com/seouleating/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_pizza_bigstar), "빅스타 피자", "02-5913-1637", "서울 강남구 논현동 123", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_pizza_domino), "도미노 피자", "02-1873-1667", "서울 강남구 역삼동 152", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_pizza_gopizza), "고 피자", "02-7641-1744", "서울 강남구 역삼동 236", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_pizza_hut), "피자헛", "02-1673-3866", "서울 강남구 논현동 38", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_pizza_mrpizza), "미스터피자", "02-7631-6137", "서울 강남구 역삼동 5", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_porkfeet_bestpork), "가장 맛있는 족발", "02-1597-1657", "서울 강남구 역삼동 3", "https://www.instagram.com/seouleating/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_porkfeet_manjok), "만족 오향족발", "02-1751-5731", "서울 강남구 논현동 55", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_porkfeet_thepork), "더 맛있는 족발", "02-5719-1495", "서울 강남구 역삼동 21", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_tteokbokki_beatteok), "배떡", "02-9175-6754", "서울 강남구 역삼동 9", "https://www.instagram.com/seouleating/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_tteokbokki_dokki), "두끼", "02-6126-5146", "서울 강남구 역삼동 1", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_tteokbokki_gokdea), "국대떡볶이", "02-5463-5176", "서울 강남구 역삼동 5", "https://www.instagram.com/seouleating/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_tteokbokki_jaws), "죠스떡볶이", "02-7563-1465", "서울 강남구 역삼동 63", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_tteokbokki_seokgwandong), "석관동 떡볶이", "02-7616-1546", "서울 강남구 역삼동 15", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/top/", false))
//        contactList.add(ContactData(Uri.parse("android.resource://com.example.hotplacecontactapp/" + R.drawable.detail_tteokbokki_yupdduk), "엽떡", "02-4561-6437", "서울 강남구 역삼동 56", "https://www.instagram.com/explore/tags/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%9B%EC%A7%91/?hl=ko", false))
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

    fun removeContactData(position : Int) {
        contactList.removeAt(position)
    }

    fun getList(): MutableList<ContactData> {
        return contactList
    }

}