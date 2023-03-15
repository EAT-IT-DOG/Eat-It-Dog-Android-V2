package com.stac.domain.usecases.search

data class SearchUseCases(
    val getResultByCategory: GetResultByCategory,
    val getResult: GetResult,
    val getResultByName: GetResultByName
)