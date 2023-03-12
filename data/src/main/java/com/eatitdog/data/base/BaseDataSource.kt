package com.eatitdog.data.base

interface BaseDataSource<REMOTE, CACHE> {
    val remote: REMOTE
    val cache: CACHE
}