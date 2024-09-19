package com.example.data.model.foreign

import com.example.domain.entity.foreign.EntryConditionData
import com.example.domain.entity.foreign.EntryConditionEntity
import com.google.gson.annotations.SerializedName

data class EntryData(
    val data: List<EntryDetail>,
    val resultCode: Int,
    val resultMsg: String,
)
data class EntryDetail(
    @SerializedName("gnrl_pspt_visa_yn") val gnrlPsptVisaYn: String,
    @SerializedName("gnrl_pspt_visa_cn") val gnrlPsptVisaCn: String,
    @SerializedName("ofclpspt_visa_yn") val ofclpsptVisaYn: String,
    @SerializedName("ofclpspt_visa_cn") val ofclpsptVisaCn: String,
    @SerializedName("dplmt_pspt_visa_yn") val dplmtPsptVisaYn: String,
    @SerializedName("dplmt_pspt_visa_cn") val dplmtPsptVisaCn: String,
    @SerializedName("nvisa_entry_evdc_cn") val nvisaEntryEvdcCn: String,
    val remark: String,
    @SerializedName("have_yn") val haveYn: String,
    @SerializedName("country_nm") val countryNm: String
)
fun EntryDetail.toDomain() = EntryConditionData(
    gnrlPsptVisaYn = gnrlPsptVisaYn,
    gnrlPsptVisaCn = gnrlPsptVisaCn,
    ofclpsptVisaYn = ofclpsptVisaYn,
    ofclpsptVisaCn = ofclpsptVisaCn,
    dplmtPsptVisaYn = dplmtPsptVisaYn,
    dplmtPsptVisaCn = dplmtPsptVisaCn,
    nvisaEntryEvdcCn = nvisaEntryEvdcCn,
    remark = remark,
    haveYn = haveYn,
    countryNm = countryNm

)
fun EntryData.toDomain() = EntryConditionEntity(
    data = data.map { it.toDomain() },
    resultCode = resultCode,
    resultMsg = resultMsg
)
