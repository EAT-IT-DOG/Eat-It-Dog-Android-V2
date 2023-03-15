package com.stac.data.network.response.data

data class SearchResponse (
    val benefit: String,
    val caution: String,
    val createdDateTime: String,
    val eatingMethod: String,
    val id: Int,
    val modifiedDateTime: String,
    val name: String,
    val safeness: String,
    val searchCount: Int,
    val symptom: String,
    val type: String
)