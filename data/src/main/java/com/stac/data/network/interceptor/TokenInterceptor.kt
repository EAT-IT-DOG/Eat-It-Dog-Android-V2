package com.stac.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
) : Interceptor {

//    private val TOKEN_ERROR = 401
//    private val TOKEN_HEADER = "Authorization"
//
//    private lateinit var token: Token
//    private lateinit var response: Response
//    private lateinit var account: AccountEntity
//
//    private val mutex = Mutex()
//
//    private val getAccountJob = CoroutineScope(appDispatcher.io).launch {
//        account = accountDataSource.getAccount()
//    }
//
    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        /*
        Token Error Test
        return Response.Builder()
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .code(TOKEN_ERROR)
            .message("세션이 만료되었습니다.")
            .body("세션이 만료되었습니다.".toResponseBody(null))
            .build()*/

//        setToken()
//        response = chain.proceedWithToken(chain.request())
//
//        if (response.code == TOKEN_ERROR) {
//            response.close()
//            chain.makeTokenRefreshCall()
//        }
//
//        return response
        TODO()
    }
//
//    private fun Interceptor.Chain.makeTokenRefreshCall() {
//        try {
//            // Refresh Token으로 새로운 AccessToken 적립
//            fetchToken()
//        } catch (e: HttpException) {
//            // 어떤 이유로 오류 발생 시
//            getTokenToLogin()
//        }
//        response = this.proceedWithToken(this.request())
//
//        if (response.code == TOKEN_ERROR) {
//            // 만약 토큰 오류 발생 시 로그인
//            try {
//                response.close()
//                response = login()
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    private fun Interceptor.Chain.login(): Response {
//        // 로그인으로 토큰 교체
//        getTokenToLogin()
//
//        // request에 토큰을 붙여서 새로운 request 생성 -> 진행
//        response = this.proceedWithToken(this.request())
//
//        return if (response.code == TOKEN_ERROR) {
//            Log.d("TokenTest", "Here is Login")
//            Response.Builder()
//                .request(this.request())
//                .protocol(Protocol.HTTP_1_1)
//                .code(TOKEN_ERROR)
//                .message("세션이 만료되었습니다.")
//                .body("세션이 만료되었습니다.".toResponseBody(null))
//                .build()
//        } else response
//    }
//
//    private fun setToken() = runBlocking(appDispatcher.io) {
//        tokenUseCases.getToken().let {
//            token = it
//        }
//    }
//
//    private fun fetchToken() = runBlocking(appDispatcher.io) {
//        tokenUseCases.updateNewToken().let { token = it }
//    }
//
//    private fun getTokenToLogin() {
//        runBlocking(appDispatcher.io) {
//            // 계정을 DB에서 받아옴
//            getAccountJob.join()
//
//            loginUseCase(LoginUseCase.Params(account.id, account.pw, false)).onEach {
//                if (it is Resource.Success) {
//                    // 성공 시 로그인 딴에서 DB에 token 값을 저장하므로 DB에서 token을 가져오는 작업 수행
//                    setToken()
//                } else if (it is Resource.Error) {
//                    Log.d("TokenTest", "Here is Get token to login")
//                    throw TokenException("세션이 만료되었습니다.")
//                }
//            }.launchIn(CoroutineScope(appDispatcher.io))
//        }
//    }
//
//    // ---------------------------------------------------------------------------------
//    private fun Interceptor.Chain.proceedWithToken(req: Request): Response =
//        req.newBuilder()
//            .addHeader(TOKEN_HEADER, "Bearer ${token.token}")
//            .build()
//            .let(::proceed)
}