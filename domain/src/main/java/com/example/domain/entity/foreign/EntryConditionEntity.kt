package com.example.domain.entity.foreign

data class EntryConditionEntity(
    val data: List<EntryConditionData>,
    val resultCode: Int,
    val resultMsg: String,
)
data class EntryConditionData(
    val gnrlPsptVisaYn: String,
    val gnrlPsptVisaCn: String,
    val ofclpsptVisaYn: String,
    val ofclpsptVisaCn: String,
    val dplmtPsptVisaYn: String,
    val dplmtPsptVisaCn: String,
    val nvisaEntryEvdcCn: String,
    val remark: String,
    val haveYn: String,
    val countryNm: String

)