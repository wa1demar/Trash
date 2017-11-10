package ua.waldemar.trash.data.repository.trash

import io.reactivex.Single
import ua.waldemar.trash.presentation.model.TrashModel

interface TrashRepository {

    fun getTrash() : Single<TrashModel>
}