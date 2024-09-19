package com.example.data.model.foreign

import com.example.domain.entity.foreign.BasicEntity
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "response")
data class BasicData(
    @Element(name = "body")
    val body: BasicBody,
    @Element(name = "header")
    val header: BasicHeader
)
@Xml(name = "header")
data class BasicHeader(
    @PropertyElement(name = "resultCode")
    val resultCode: Int,
    @PropertyElement(name = "resultMsg")
    val resultMsg: String
)
@Xml(name = "body")
data class BasicBody(
    @Element(name = "items")
    val items: BasicItems,
    @PropertyElement(name = "numOfRows")
    val numOfRows: Int,
    @PropertyElement(name = "pageNo")
    val pageNo: Int,
    @PropertyElement(name = "totalCount")
    val totalCount: Int
)
@Xml(name = "items")
data class BasicItems(
    @Element(name = "item")
    val item: List<BasicItem>
)
@Xml(name= "item")
data class BasicItem(
    @PropertyElement(name = "basic")
    val basic: String,
    @PropertyElement(name = "continent")
    val continent: String,
    @PropertyElement(name = "wrtDt")
    val wrtDt: String,
    @PropertyElement(name = "countryName")
    val countryName: String,
    @PropertyElement(name = "countryEnName")
    val countryEnName: String,
    @PropertyElement(name = "id")
    val id: Int,
    @PropertyElement(name = "imgUrl")
    val imgUrl: String
)

fun BasicItem.toDomain() = BasicEntity(
    //basic = Html.fromHtml(basic, Html.FROM_HTML_MODE_LEGACY).toString().replace("<div>","").replace("</div>","").replace("<br>","\n"),
    basic = basic.replace("&lt;br&gt;","\r").replace("&lt;/div&gt;","").replace("&lt;div&gt;","").replace("&#xD;",""),
    continent = continent,
    wrtDt = wrtDt,
    countryName = countryName
)