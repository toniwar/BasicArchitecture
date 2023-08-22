package ru.otus.basicarchitecture.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.otus.basicarchitecture.app.App




@MainComponentScope
@Component
interface MainComponent {
    fun inject(app:App)
    fun provideContext(): Context


    @Component.Factory
    interface AppComponentFactory{
        fun create(
            @BindsInstance
            context: Context
        ): MainComponent

    }
}





