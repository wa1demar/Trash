package ua.waldemar.trash.data.network.exception

import retrofit2.Response
import retrofit2.Retrofit
import ua.waldemar.trash.data.network.enumclass.Kind
import java.io.IOException

class RetrofitException(override val message: String?,
                        override val cause: Throwable?,
                        val url: String?,
                        val response: Response<*>?,
                        val kind: Kind,
                        val retrofit: Retrofit?) : RuntimeException() {

    companion object {
        fun httpError(url: String, response: Response<*>, retrofit: Retrofit?): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            return RetrofitException(message, null, url, response, Kind.HTTP, retrofit)
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, exception, null, null, Kind.NETWORK, null)
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(exception.message, exception, null, null, Kind.UNEXPECTED, null)
        }
    }

    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (response?.errorBody() == null || retrofit == null) {
            return null
        }
        val converter = retrofit.responseBodyConverter<T>(type, arrayOfNulls(0))
        return converter.convert(response.errorBody())
    }

}