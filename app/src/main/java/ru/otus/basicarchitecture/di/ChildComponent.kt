package ru.otus.basicarchitecture.di


import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import ru.otus.basicarchitecture.data.DataStoreManagerImpl
import ru.otus.basicarchitecture.data.repositories.UserRepositoryImpl
import ru.otus.basicarchitecture.domain.repositories.DataStoreManager
import ru.otus.basicarchitecture.domain.repositories.UserRepository
import ru.otus.basicarchitecture.presentation.activity.MainActivity
import ru.otus.basicarchitecture.presentation.fragments.StartRegistrationFragment
import ru.otus.basicarchitecture.presentation.view_models.StartRegistrationFragmentVM

@ChildComponentScope
@Component(
    dependencies = [MainComponent::class],
    modules = [ViewModelsModule::class, RepositoryModule::class, DataModule::class]
)

interface ChildComponent {

    fun inject(activity: MainActivity)

    fun injectStartRegistrationFragment(fragment: StartRegistrationFragment)

    fun injectVM(vm: StartRegistrationFragmentVM)

    @Component.Factory
    interface ActivityComponentFactory{
        fun create(appComponent: MainComponent): ChildComponent
    }
}

@Module
interface ViewModelsModule {
    @IntoMap
    @ViewModelKey(StartRegistrationFragmentVM::class)
    @Binds
    fun bindsStartRegistrationFragmentVM(impl: StartRegistrationFragmentVM): ViewModel
}

@Module
interface RepositoryModule{
    @ChildComponentScope
    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}

@Module
interface DataModule{
    @ChildComponentScope
    @Binds
    fun bindDataStoreManager(impl: DataStoreManagerImpl): DataStoreManager
}






