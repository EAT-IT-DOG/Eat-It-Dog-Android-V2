package com.stac.data.datasource

import com.stac.data.base.BaseDataSource
import com.stac.data.database.cache.AccountCache
import com.stac.data.database.entity.AccountEntity
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val remote: Any,
    override val cache: AccountCache
) : BaseDataSource<Any, AccountCache> {

    suspend fun getAccount(): AccountEntity = cache.getAccount()

    suspend fun insertAccount(accountEntity: AccountEntity) = cache.insertAccount(accountEntity)

    suspend fun deleteAccount() = cache.deleteAccount()
}
