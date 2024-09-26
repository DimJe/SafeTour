package com.example.data.model.naver

import com.example.domain.entity.naver.BlogEntity
import com.example.domain.entity.naver.BlogItem
import com.google.gson.annotations.SerializedName

data class BlogData(
    val items: List<BlogDetail>
)
data class BlogDetail(
    val title: String,
    val link: String,
    @SerializedName("postdate") val postDate: String,
)
fun BlogDetail.toDomain() = BlogItem(
    title = title,
    link = link,
    postDate = postDate
)
fun BlogData.toDomain() = BlogEntity(
    items = items.map { it.toDomain() }
)