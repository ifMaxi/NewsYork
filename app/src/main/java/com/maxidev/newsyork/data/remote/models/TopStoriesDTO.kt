package com.maxidev.newsyork.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopStoriesDTO(
    @SerialName("status")
    val status: String? = "",
    @SerialName("copyright")
    val copyright: String? = "",
    @SerialName("section")
    val section: String? = "",
    @SerialName("last_updated")
    val lastUpdated: String? = "",
    @SerialName("num_results")
    val numResults: Int? = 0,
    @SerialName("results")
    val results: List<Result?>? = listOf()
) {
    @Serializable
    data class Result(
        @SerialName("section")
        val section: String? = "",
        @SerialName("subsection")
        val subsection: String? = "",
        @SerialName("title")
        val title: String? = "",
        @SerialName("abstract")
        val `abstract`: String? = "",
        @SerialName("url")
        val url: String? = "",
        @SerialName("uri")
        val uri: String? = "",
        @SerialName("byline")
        val byline: String? = "",
        @SerialName("item_type")
        val itemType: String? = "",
        @SerialName("updated_date")
        val updatedDate: String? = "",
        @SerialName("created_date")
        val createdDate: String? = "",
        @SerialName("published_date")
        val publishedDate: String? = "",
        @SerialName("material_type_facet")
        val materialTypeFacet: String? = "",
        @SerialName("kicker")
        val kicker: String? = "",
        @SerialName("des_facet")
        val desFacet: List<String?>? = listOf(),
        @SerialName("org_facet")
        val orgFacet: List<String?>? = listOf(),
        @SerialName("per_facet")
        val perFacet: List<String?>? = listOf(),
        @SerialName("geo_facet")
        val geoFacet: List<String?>? = listOf(),
        @SerialName("multimedia")
        val multimedia: List<Multimedia?>? = listOf(),
        @SerialName("short_url")
        val shortUrl: String? = ""
    ) {
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