package com.example.domain.entity.naver

data class ImageEntity(
    val items: List<ImageItem>
)
data class ImageItem(
    val link: String,
    val thumbnail: String
)
