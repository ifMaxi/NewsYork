package com.maxidev.newsyork.homenews.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsWireDTO(
    @SerialName("status")
    val status: String? = "",
    @SerialName("copyright")
    val copyright: String? = "",
    @SerialName("num_results")
    val numResults: Int? = 0,
    @SerialName("results")
    val results: List<Result?>? = listOf()
) {
    @Serializable
    data class Result(
        @SerialName("slug_name")
        val slugName: String? = "",
        @SerialName("section")
        val section: String? = "",
        @SerialName("subsection")
        val subsection: String? = "",
        @SerialName("title")
        val title: String? = "",
        @SerialName("abstract")
        val `abstract`: String? = "",
        @SerialName("uri")
        val uri: String? = "",
        @SerialName("url")
        val url: String? = "",
        @SerialName("byline")
        val byline: String? = "",
        @SerialName("item_type")
        val itemType: String? = "",
        @SerialName("source")
        val source: String? = "",
        @SerialName("updated_date")
        val updatedDate: String? = "",
        @SerialName("created_date")
        val createdDate: String? = "",
        @SerialName("published_date")
        val publishedDate: String? = "",
        @SerialName("first_published_date")
        val firstPublishedDate: String? = "",
        @SerialName("material_type_facet")
        val materialTypeFacet: String? = "",
        @SerialName("kicker")
        val kicker: String? = "",
        @SerialName("subheadline")
        val subheadline: String? = "",
        @SerialName("des_facet")
        val desFacet: List<String?>? = listOf(),
        @SerialName("org_facet")
        val orgFacet: List<String?>? = listOf(),
        @SerialName("per_facet")
        val perFacet: List<String?>? = listOf(),
        @SerialName("geo_facet")
        val geoFacet: List<String?>? = listOf(),
        @SerialName("related_urls")
        val relatedUrls: List<RelatedUrl?>? = listOf(),
        @SerialName("multimedia")
        val multimedia: List<Multimedia?>? = listOf()
    ) {
        @Serializable
        data class RelatedUrl(
            @SerialName("suggested_link_text")
            val suggestedlinktext: String?,
            @SerialName("url")
            val url: String?
        )

        @Serializable
        data class Multimedia(
            @SerialName("url")
            val url: String? = "",
            @SerialName("format")
            val format: String? = "",
            @SerialName("height")
            val height: Int? = 0,
            @SerialName("width")
            val width: Int? = 0,
            @SerialName("type")
            val type: String? = "",
            @SerialName("subtype")
            val subtype: String? = "",
            @SerialName("caption")
            val caption: String? = "",
            @SerialName("copyright")
            val copyright: String? = ""
        )
    }
}