package ua.waldemar.trash.di

import org.koin.android.module.AndroidModule
import ua.waldemar.trash.domain.usecase.TrashUseCase

class InteractorModule : AndroidModule() {

    override fun context() = applicationContext {
        provide { TrashUseCase(get()) }
    }
}