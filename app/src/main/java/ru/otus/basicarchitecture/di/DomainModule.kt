package ru.otus.basicarchitecture.di

import dagger.Module
import dagger.Provides
import ru.otus.basicarchitecture.domain.repository.UserRepository
import ru.otus.basicarchitecture.domain.usecases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.SaveDataUseCase

@Module
class DomainModule {

    @Provides
    fun provideGetDataUseCase(userRepository: UserRepository):GetDataUseCase{
        return GetDataUseCase(userRepository)
    }

    @Provides
    fun provideSaveDataUseCase(userRepository: UserRepository):SaveDataUseCase{
        return SaveDataUseCase(userRepository)
    }
}