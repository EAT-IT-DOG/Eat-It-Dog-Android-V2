package com.stac.domain.util

import org.json.JSONObject
import retrofit2.HttpException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Utils {

    const val TOKEN_EXCEPTION = "token_exception"

    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun encryptSHA512(target: String): String {
        val messageDigest =
            MessageDigest.getInstance("SHA-512")
        val encryptedPassword = StringBuilder()
        messageDigest.update(target.toByteArray())
        val buffer = messageDigest.digest()
        for (temp in buffer) {
            var sb =
                StringBuilder(Integer.toHexString(temp.toInt()))
            while (sb.length < 2) {
                sb.insert(0, "0")
            }
            sb = StringBuilder(sb.substring(sb.length - 2))
            encryptedPassword.append(sb)
        }
        return encryptedPassword.toString()
    }

    fun convertErrorBody(throwable: HttpException): String {
        return try {
            val errorBody = JSONObject(throwable.response()?.errorBody()!!.string())
            errorBody.getString("message")
        } catch (e: Exception) {
            "알 수 없는 오류가 발생했습니다. 잠시만 기다려주세요."
        }
    }

    const val NETWORK_ERROR_MESSAGE = "서버에 도달할 수 없습니다. 네트워크 상태를 확인해 주세요."

    fun String.getUrlFileName(): String {
        val fileName: String = this.substring(this.lastIndexOf('/') + 1, this.length)
        return fileName.substring(0, fileName.lastIndexOf('.'))
    }

    fun String.getUrlExtension(): String {
        val fileName: String = this.substring(this.lastIndexOf('/') + 1, this.length)
        return fileName.substring(fileName.lastIndexOf('.') + 1)
    }
}