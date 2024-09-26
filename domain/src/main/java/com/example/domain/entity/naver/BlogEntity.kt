package com.example.domain.entity.naver

data class BlogEntity(
    val items: List<BlogItem>,

)
data class BlogItem(
    val title: String,
    val link: String,
    val postDate: String,
)