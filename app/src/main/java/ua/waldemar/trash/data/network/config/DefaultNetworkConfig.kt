package ua.waldemar.trash.data.network.config

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import ua.waldemar.trash.BaseApiPathConst

class DefaultNetworkConfig : NetworkConfiguration {

    companion object {
        private val HEADER_SESSION_TOKEN = "Session-Token"
        private val CONNECTION_TIMEOUT_SECONDS = 40
    }

    override val mBaseUrl: String = BaseApiPathConst.API_BASE_URL + BaseApiPathConst.API_PATH

    override val mConnectionTimeout: Int = CONNECTION_TIMEOUT_SECONDS

    override val mInterceptors: List<Interceptor> = arrayListOf(getAuthRequestInterceptor(), getLoggingInterceptor())

    fun getLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    fun getAuthRequestInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            val authHeaderKey = this@DefaultNetworkConfig.mAuthTokenHeaderKey
            val authHeaderToken = this@DefaultNetworkConfig.mAuthToken

            if (authHeaderToken != null) {
                request = request.newBuilder()
                        .addHeader(authHeaderKey, authHeaderToken)
                        .build()
            }
            chain.proceed(request)
        }
    }

    private val mAuthTokenHeaderKey = HEADER_SESSION_TOKEN

    private val mAuthToken : String? = null
}