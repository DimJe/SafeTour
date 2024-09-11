package com.example.domain.entity.foreign

data class ContactEntity(

    val data: List<Contact>,
    val resultCode: Int,
    val resultMsg: String,
)
data class Contact(
    val contactRemark: String,
    val dangMapDownloadUrl: String,
    val mapDownloadUrl: String,
    val wrtDt: String,
    val countryNm: String
)
