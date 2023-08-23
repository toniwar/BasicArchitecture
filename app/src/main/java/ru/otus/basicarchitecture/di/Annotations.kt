package ru.otus.basicarchitecture.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainComponentScope

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ChildComponentScope

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)