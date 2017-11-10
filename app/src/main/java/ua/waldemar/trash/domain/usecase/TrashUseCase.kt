package ua.waldemar.trash.domain.usecase

import io.reactivex.Single
import ua.waldemar.trash.data.repository.trash.TrashRepository
import ua.waldemar.trash.domain.base.UseCase
import ua.waldemar.trash.presentation.model.TrashModel

class TrashUseCase(private val mTrashRepository: TrashRepository) : UseCase.RxSingle<TrashModel>() {

    override fun build(): Single<TrashModel> = mTrashRepository.getTrash()

}