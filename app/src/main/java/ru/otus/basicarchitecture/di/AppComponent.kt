package ru.otus.basicarchitecture.di

import dagger.Component
import ru.otus.basicarchitecture.presentation.activity.MainActivity
import ru.otus.basicarchitecture.presentation.fragments.Fragment1


@Component(modules = [AppModule::class,DataModule::class, DomainModule::class])
interface AppComponent {

    fun injectActivity(activity: MainActivity)

    fun injectFragment1(fragment: Fragment1)

}