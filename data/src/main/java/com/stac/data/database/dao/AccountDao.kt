package com.stac.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.stac.data.base.remote.BaseDao
import com.stac.data.database.entity.AccountEntity

@Dao
interface AccountDao : BaseDao<AccountEntity> {

    @Query("Select * FROM account_table WHERE idx = 0")
    suspend fun getAccount(): AccountEntity

    @Query("DELETE FROM account_table")
    suspend fun deleteAccount()
}
