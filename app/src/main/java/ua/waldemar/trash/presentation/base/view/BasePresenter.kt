package ua.waldemar.trash.presentation.base.view

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> : Presenter<V> {

    var mBaseView: WeakReference<V>? = null

    override var mView: V? = null
        get() = mBaseView?.get()

    private val mCompositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        mBaseView = WeakReference(view)
    }

    override fun isViewAttached(): Boolean = mBaseView?.get() != null ?: false


    override fun detachView() {
        mCompositeDisposable.clear()
        mBaseView?.clear()
        mBaseView = null
    }
}