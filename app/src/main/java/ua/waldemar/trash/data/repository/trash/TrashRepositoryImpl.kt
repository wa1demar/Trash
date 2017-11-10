package ua.waldemar.trash.data.repository.trash

import io.reactivex.Single
import ua.waldemar.trash.presentation.model.TrashModel

class TrashRepositoryImpl : TrashRepository {

    override fun getTrash(): Single<TrashModel> {
        return Single.just(TrashModel(15))
    }
}