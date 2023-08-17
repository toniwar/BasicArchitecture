package ru.otus.basicarchitecture.app

import android.app.Application
import ru.otus.basicarchitecture.di.AppComponent
import ru.otus.basicarchitecture.di.DaggerAppComponent




class App: Application() {

    private val appComponent by lazy { DaggerAppComponent.factory().create(this) }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun provideAppComponent(): AppComponent {
        return appComponent
    }

    companion object{
        private val app = App()
        fun provideApp() = app
    }



}