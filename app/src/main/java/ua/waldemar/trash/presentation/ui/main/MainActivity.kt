package ua.waldemar.trash.presentation.ui.main

import android.os.Bundle
import org.koin.android.ext.android.inject
import ua.waldemar.trash.R
import ua.waldemar.trash.di.ActivityModule
import ua.waldemar.trash.presentation.base.view.BaseActivity
import ua.waldemar.trash.presentation.base.view.toast

class MainActivity : BaseActivity<MainContract.View, MainContract.MainPresenter>(), MainContract.View {

    override val contextName = ActivityModule.CTX_MAIN_ACTIVITY

    override val mPresenter by inject<MainContract.MainPresenter>()

    override val mLayoutResource: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.getTrash()
    }

    override fun showId(mId: Long) {
        toast(mId.toString())
    }
}