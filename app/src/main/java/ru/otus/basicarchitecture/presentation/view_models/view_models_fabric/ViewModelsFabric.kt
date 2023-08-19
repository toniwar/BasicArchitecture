package ru.otus.basicarchitecture.presentation.view_models.view_models_fabric

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.otus.basicarchitecture.di.ChildComponentScope
import javax.inject.Inject
import javax.inject.Provider

@ChildComponentScope
class ViewModelsFabric @Inject constructor(
    private val vMProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return vMProviders[modelClass]?.get() as T
    }
}