package com.stac.domain.usecases.search

data class SearchUseCases(
    val getResultByCategory: GetResultByCategory,
    val getResultAll: GetResultAll,
    val getResultByName: GetResultByName,
    val getResult: GetResult
)
