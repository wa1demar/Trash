package ua.waldemar.trash.di

import org.koin.android.module.AndroidModule
import ua.waldemar.trash.data.repository.trash.TrashRepository
import ua.waldemar.trash.data.repository.trash.TrashRepositoryImpl

class RepositoryModule: AndroidModule() {

    override fun context() = applicationContext {
        provide { TrashRepositoryImpl() } bind (TrashRepository::class)
    }
}