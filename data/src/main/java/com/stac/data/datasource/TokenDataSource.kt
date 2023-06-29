package com.stac.data.datasource

import android.util.Base64
import com.stac.data.base.BaseDataSource
import com.stac.data.database.cache.TokenCache
import com.stac.data.database.entity.TokenEntity
import com.stac.data.mapper.toEntity
import com.stac.data.mapper.toModel
import com.stac.data.network.remote.AuthRemote
import com.stac.domain.model.auth.User
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    override val remote: AuthRemote,
    override val cache: TokenCache
) : BaseDataSource<AuthRemote, TokenCache> {

    private val PAYLOAD_MEMBER_ID = "memberId"

    suspend fun insertToken(token: User) = cache.insertToken(token.toEntity())

    suspend fun getToken(): TokenEntity = cache.getToken()

    suspend fun updateNewToken(): TokenEntity =
        getToken()
            .let { tokenEntity -> tokenEntity.toModel() }
            .let { insertNewToken(it) }

    suspend fun deleteToken() = cache.deleteToken()

    private suspend fun insertNewToken(token: User): TokenEntity =
        remote.getNewToken(token.refresh_token)
            .let { newToken -> TokenEntity(newToken, token.refresh_token) }
            .also { cache.insertToken(it) }

    suspend fun getMyId(): String = getId(getToken().toModel())

    private fun getId(token: User): String {
        return try {
            val payload = decodedPayloadObject(token.access_token)
            payload!!.getString(PAYLOAD_MEMBER_ID)
        } catch (ignore: JSONException) {
            throw Throwable("아이디 오류")
        }
    }

    private fun decodedPayloadObject(tokenString: String): JSONObject? {
        return try {
            val split = tokenString.split(".").toTypedArray()
            JSONObject(String(Base64.decode(split[1], Base64.DEFAULT)))
        } catch (ignore: JSONException) {
            null
        }
    }
}
