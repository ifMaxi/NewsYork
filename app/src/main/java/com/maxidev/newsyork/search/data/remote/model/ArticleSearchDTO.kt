package com.maxidev.newsyork.search.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleSearchDTO(
    @SerialName("status")
    val status: String? = "",
    @SerialName("copyright")
    val copyright: String? = "",
    @SerialName("response")
    val response: Response? = Response()
) {
    @Serializable
    data class Response(
        @SerialName("docs")
        val docs: List<Doc?>? = listOf(),
        @SerialName("meta")
        val meta: Meta? = Meta()
    ) {
        @Serializable
        data class Doc(
            @SerialName("abstract")
            val `abstract`: String? = "",
            @SerialName("web_url")
            val webUrl: String? = "",
            @SerialName("snippet")
            val snippet: String? = "",
            @SerialName("lead_paragraph")
            val leadParagraph: String? = "",
            @SerialName("source")
            val source: String? = "",
            @SerialName("multimedia")
            val multimedia: List<Multimedia?>? = listOf(),
            @SerialName("headline")
            val headline: Headline? = Headline(),
            @SerialName("keywords")
            val keywords: List<Keyword?>? = listOf(),
            @SerialName("pub_date")
            val pubDate: String? = "",
            @SerialName("document_type")
            val documentType: String? = "",
            @SerialName("news_desk")
            val newsDesk: String? = "",
            @SerialName("section_name")
            val sectionName: String? = "",
            @SerialName("byline")
            val byline: Byline? = Byline(),
            @SerialName("type_of_material")
            val typeOfMaterial: String? = "",
            @SerialName("_id")
            val id: String? = "",
            @SerialName("word_count")
            val wordCount: Int? = 0,
            @SerialName("uri")
            val uri: String? = "",
            @SerialName("subsection_name")
            val subsectionName: String? = ""
        ) {
            @Serializable
            data class Multimedia(
                @SerialName("rank")
                val rank: Int? = 0,
                @SerialName("subtype")
                val subtype: String? = "",
                @SerialName("caption")
                val caption: String? = "",
                @SerialName("credit")
                val credit: String? = "",
                @SerialName("type")
                val type: String? = "",
                @SerialName("url")
                val url: String? = "",
                @SerialName("height")
                val height: Int? = 0,
                @SerialName("width")
                val width: Int? = 0,
                @SerialName("legacy")
                val legacy: Legacy? = Legacy(),
                @SerialName("subType")
                val subType: String? = "",
                @SerialName("crop_name")
                val cropName: String? = ""
            ) {
                @Serializable
                data class Legacy(
                    @SerialName("xlarge")
                    val xlarge: String? = "",
                    @SerialName("xlargewidth")
                    val xlargewidth: Int? = 0,
                    @SerialName("xlargeheight")
                    val xlargeheight: Int? = 0,
                    @SerialName("thumbnail")
                    val thumbnail: String? = "",
                    @SerialName("thumbnailwidth")
                    val thumbnailwidth: Int? = 0,
                    @SerialName("thumbnailheight")
                    val thumbnailheight: Int? = 0,
                    @SerialName("widewidth")
                    val widewidth: Int? = 0,
                    @SerialName("wideheight")
                    val wideheight: Int? = 0,
                    @SerialName("wide")
                    val wide: String? = ""
                )
            }

            @Serializable
            data class Headline(
                @SerialName("main")
                val main: String? = "",
                @SerialName("kicker")
                val kicker: String? = "",
                @SerialName("content_kicker")
                val contentKicker: String? = "",
                @SerialName("print_headline")
                val printHeadline: String? = "",
                @SerialName("name")
                val name: String? = "",
                @SerialName("seo")
                val seo: String? = "",
                @SerialName("sub")
                val sub: String? = ""
            )

            @Serializable
            data class Keyword(
                @SerialName("name")
                val name: String? = "",
                @SerialName("value")
                val value: String? = "",
                @SerialName("rank")
                val rank: Int? = 0,
                @SerialName("major")
                val major: String? = ""
            )

            @Serializable
            data class Byline(
                @SerialName("original")
                val original: String? = "",
                @SerialName("person")
                val person: List<Person?>? = listOf(),
                @SerialName("organization")
                val organization: String? = ""
            ) {
                @Serializable
                data class Person(
                    @SerialName("firstname")
                    val firstname: String? = "",
                    @SerialName("middlename")
                    val middlename: String? = "",
                    @SerialName("lastname")
                    val lastname: String? = "",
                    @SerialName("qualifier")
                    val qualifier: String? = "",
                    @SerialName("title")
                    val title: String? = "",
                    @SerialName("role")
                    val role: String? = "",
                    @SerialName("organization")
                    val organization: String? = "",
                    @SerialName("rank")
                    val rank: Int? = 0
                )
            }
        }

        @Serializable
        data class Meta(
            @SerialName("hits")
            val hits: Int? = 0,
            @SerialName("offset")
            val offset: Int? = 0,
            @SerialName("time")
            val time: Int? = 0
        )
    }
}