package ua.waldemar.trash.presentation.ui.main

import ua.waldemar.trash.domain.base.UseCaseObserver
import ua.waldemar.trash.domain.usecase.TrashUseCase
import ua.waldemar.trash.presentation.base.view.BasePresenter
import ua.waldemar.trash.presentation.model.TrashModel

class MainPresenter(private val mTrashUseCase: TrashUseCase) : BasePresenter<MainContract.View>(), MainContract.MainPresenter {

    override fun getTrash() {
        mTrashUseCase.execute(TrashSubscriber())
    }

    inner class TrashSubscriber : UseCaseObserver.RxSingle<TrashModel>() {
        override fun onStart() {

        }

        override fun onSuccess(value: TrashModel) {
            mView?.showId(value.mId)
        }

        override fun onError(e: Throwable) {

        }
    }

}