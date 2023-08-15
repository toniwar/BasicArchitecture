package ru.otus.basicarchitecture.di



import dagger.Binds
import dagger.Component
import dagger.Module
import ru.otus.basicarchitecture.data.repositories.UserRepositoryImpl
import ru.otus.basicarchitecture.domain.repositories.UserRepository
import ru.otus.basicarchitecture.presentation.activity.MainActivity




@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface ActivityComponentFactory{

        fun create(appComponent: AppComponent): ActivityComponent
    }


}

@Module
abstract class ActivityModule{

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}





