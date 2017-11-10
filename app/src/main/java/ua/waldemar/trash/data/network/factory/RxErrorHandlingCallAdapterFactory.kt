package ua.waldemar.trash.data.network.factory

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    private val mOriginal: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    companion object {
        fun create(): CallAdapter.Factory = RxErrorHandlingCallAdapterFactory()
    }

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *> {
        return RxCallAdapterWrapper(retrofit, mOriginal.get(returnType, annotations, retrofit))
    }
}