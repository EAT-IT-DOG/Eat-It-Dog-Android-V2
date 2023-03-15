package com.stac.domain.base

import com.stac.domain.exception.TokenException
import com.stac.domain.util.Resource
import com.stac.domain.util.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

abstract class UseCase<PR, R> {

    abstract operator fun invoke(params: PR): Flow<Resource<R>>

    fun execute(action: suspend () -> R): Flow<Resource<R>> = flow {
        try {
            emit(Resource.Loading<R>())
            val result = action.invoke()
            emit(Resource.Success<R>(result))
        } catch (e: HttpException) {
            if (e.code() == 401) emit(Resource.Error<R>(Utils.TOKEN_EXCEPTION))
            else emit(Resource.Error<R>(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error<R>(Utils.NETWORK_ERROR_MESSAGE))
        } catch (e: TokenException) {
            emit(Resource.Error<R>(Utils.TOKEN_EXCEPTION))
        }
    }
}