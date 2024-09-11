package com.example.data.model

import com.example.domain.entity.foreign.Contact
import com.example.domain.entity.foreign.ContactEntity
import com.google.gson.annotations.SerializedName

data class ContactData(
    val data: List<ContactDetail>,
    val resultCode: Int,
    val resultMsg: String
)
data class ContactDetail(
    @SerializedName("contact_remark") val contactRemark: String,
    @SerializedName("dang_map_download_url") val dangMapDownloadUrl: String,
    @SerializedName("map_download_url") val mapDownloadUrl: String,
    @SerializedName("wrt_dt") val wrtDt: String,
    @SerializedName("country_nm") val countryNm: String
)
fun ContactDetail.toDomain() = Contact(
    contactRemark = contactRemark,
    dangMapDownloadUrl = dangMapDownloadUrl,
    mapDownloadUrl = mapDownloadUrl,
    wrtDt = wrtDt,
    countryNm = countryNm
)
fun ContactData.toDomain() = ContactEntity(
    data = data.map { it.toDomain() },
    resultCode = resultCode,
    resultMsg = resultMsg
)
