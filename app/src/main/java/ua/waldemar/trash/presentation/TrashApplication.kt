package ua.waldemar.trash.presentation

import android.app.Application
import org.koin.android.ext.android.startAndroidContext
import ua.waldemar.trash.di.*

class TrashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startAndroidContext(this, listOf(AppModule(),
                ActivityModule(),
                InteractorModule(),
                NetworkModule(),
                CacheModule(),
                RepositoryModule()))
    }
}