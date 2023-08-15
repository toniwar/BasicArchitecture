package ru.otus.basicarchitecture.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.otus.basicarchitecture.app.App

@Component
interface AppComponent {
    fun inject(app:App)

    fun provideContext(): Context



    @Component.Builder
    interface AppComponentBuilder{

        @BindsInstance
        fun context(context: Context): AppComponentBuilder

        fun build(): AppComponent
    }

}



@Module
class DataModule{

}
