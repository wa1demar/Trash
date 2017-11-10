package ua.waldemar.trash.domain.base

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class UseCase<out T> where T : Any {

    internal val disposables = CompositeDisposable()

    fun dispose() = disposables.dispose()

    abstract fun build(): T

    abstract class RxSingle<T> : UseCase<Single<T>>() {
        fun execute(observer: UseCaseObserver.RxSingle<T>) =
                disposables.add(build()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer))

        fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) =
                disposables.add(build()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onSuccess, onError))
    }

    abstract class RxObservable<T> : UseCase<Observable<T>>() {
        fun execute(observer: UseCaseObserver.RxObservable<T>) =
                disposables.add(build()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer))

        fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit) {
            disposables.add(build()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext, onError))
        }
    }

    abstract class RxFlowable<T> : UseCase<Flowable<T>>() {
        fun execute(subscriber: UseCaseObserver.RxFlowable<T>) =
                disposables.add(build()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(subscriber))

        fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit) {
            disposables.add(build()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext, onError))
        }
    }

    abstract class RxCompletable<in P> : UseCase<Completable>() {
        fun execute(params: P? = null) = execute({})

        fun execute(onComplete: () -> Unit = {}) =
                disposables.add(build()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onComplete))
    }

    class None
}