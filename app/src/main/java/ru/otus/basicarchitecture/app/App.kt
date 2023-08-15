package ru.otus.basicarchitecture.app

import android.app.Application
import ru.otus.basicarchitecture.di.AppComponent
import ru.otus.basicarchitecture.di.DaggerAppComponent

class App: Application() {

    private val appComponent by lazy { DaggerAppComponent
        .builder()
        .context(this)
        .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun provideAppComponent(): AppComponent {
        return appComponent
    }



}