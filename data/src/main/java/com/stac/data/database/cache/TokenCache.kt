package com.stac.data.database.cache

import android.app.Application
import com.stac.data.database.dao.TokenDao
import com.stac.data.base.BaseCache
import com.stac.data.database.entity.TokenEntity
import javax.inject.Inject

class TokenCache @Inject constructor(application: Application) : BaseCache(application) {
    private val tokenDao: TokenDao = database.tokenDao()
    suspend fun insertToken(tokenEntity: TokenEntity) = tokenDao.insert(tokenEntity)
    suspend fun deleteToken() = tokenDao.deleteToken()
    suspend fun getToken(): TokenEntity = tokenDao.getToken()
}
