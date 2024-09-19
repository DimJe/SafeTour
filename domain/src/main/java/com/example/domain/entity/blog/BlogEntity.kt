package com.example.domain.entity.blog

data class BlogEntity(
    val items: List<BlogItem>,

)
data class BlogItem(
    val title: String,
    val link: String,
    val postDate: String,
)