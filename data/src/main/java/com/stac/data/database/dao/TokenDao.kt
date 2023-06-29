package com.stac.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.stac.data.base.remote.BaseDao
import com.stac.data.database.entity.TokenEntity

@Dao
interface TokenDao : BaseDao<TokenEntity> {

    @Query("SELECT * FROM token_table WHERE idx = 0")
    suspend fun getToken(): TokenEntity

    @Query("DELETE FROM token_table")
    suspend fun deleteToken()
}
