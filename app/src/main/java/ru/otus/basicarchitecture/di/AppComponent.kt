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


    @Component.Factory
    interface AppComponentFactory{


        fun create(
            @BindsInstance
            context: Context
        ): AppComponent

    }

}


@Module
class DataModule{

}


