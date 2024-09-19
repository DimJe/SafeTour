package com.example.data.model.blog

import com.example.domain.entity.blog.BlogEntity
import com.example.domain.entity.blog.BlogItem
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