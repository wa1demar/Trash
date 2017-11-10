package ua.waldemar.trash.presentation.base.view

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import org.koin.android.contextaware.ContextAwareActivity

abstract class BaseActivity<V : BaseView, out P : Presenter<V>> : ContextAwareActivity(), BaseView {

    private var mRootView: android.view.View? = null

    protected abstract val mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflatedView = LayoutInflater.from(this).inflate(mLayoutResource, null)
        if (inflatedView is CoordinatorLayout) {
            mRootView = inflatedView
        } else {
            mRootView = CoordinatorLayout(this)
            (mRootView as CoordinatorLayout).addView(inflatedView,
                    CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,
                            CoordinatorLayout.LayoutParams.MATCH_PARENT))
        }

        setContentView(mRootView)

        mPresenter.attachView(this as V)
    }

    public override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }
}
