package ua.waldemar.trash.presentation.ui.main

import ua.waldemar.trash.presentation.base.view.BaseView
import ua.waldemar.trash.presentation.base.view.Presenter

interface MainContract {

    interface View : BaseView {
        fun showId(mId: Long)

    }

    interface MainPresenter : Presenter<View> {
        fun getTrash()
    }
}