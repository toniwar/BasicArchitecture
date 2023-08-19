package ru.otus.basicarchitecture.app

import android.app.Application
import ru.otus.basicarchitecture.di.DaggerMainComponent
import ru.otus.basicarchitecture.di.MainComponent

class App: Application() {

    private val mainComponent by lazy { DaggerMainComponent.factory().create(this) }

    override fun onCreate() {
        super.onCreate()
        mainComponent.inject(this)
    }

    fun provideMainComponent(): MainComponent {
        return mainComponent
    }

    companion object{
        private val app = App()
        fun provideApp() = app
    }



}