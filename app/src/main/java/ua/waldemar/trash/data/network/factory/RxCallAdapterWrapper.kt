package ua.waldemar.trash.data.network.factory

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import ua.waldemar.trash.data.network.exception.RetrofitException
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class RxCallAdapterWrapper<R>(val mRetrofit: Retrofit?, val mWrapped: CallAdapter<R, *>) : CallAdapter<R, Any> {

    override fun responseType(): Type = mWrapped.responseType()

    override fun adapt(call: Call<R>?): Any =
            convert(mWrapped.adapt(call)).onErrorResumeNext(Function { Observable.error(asRetrofitException(it)) })

    private fun convert(o: Any): Observable<*> {
        return if (o is Completable)
            o as Observable<*>
        else
            o as Observable<*>
    }

    private fun asRetrofitException(throwable: Throwable): RetrofitException {
        if (throwable is HttpException) {
            val response = throwable.response()
            return RetrofitException.httpError(response.raw().request().url().toString(), response, mRetrofit)
        }

        if (throwable is TimeoutException ||
                throwable is ConnectException ||
                throwable is SocketTimeoutException ||
                throwable is UnknownHostException) {
            return RetrofitException.networkError(IOException(throwable.message, throwable))
        }

        return RetrofitException.unexpectedError(throwable)
    }
}