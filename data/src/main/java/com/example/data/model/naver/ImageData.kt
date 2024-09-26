package com.example.data.model.naver

import com.example.domain.entity.naver.ImageEntity
import com.example.domain.entity.naver.ImageItem

data class ImageData(
    val items: List<ImageDetail>
)
data class ImageDetail(
    val link: String,
    val thumbnail: String
)
fun ImageDetail.toDomain() = ImageItem(
    link = link,
    thumbnail = thumbnail
)
fun ImageData.toDomain() = ImageEntity(
    items.map { it.toDomain() }
)
