package ru.otus.basicarchitecture.presentation.view_models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.otus.basicarchitecture.domain.entities.User
import ru.otus.basicarchitecture.domain.use_cases.DownloadUserProfileUseCase
import ru.otus.basicarchitecture.domain.use_cases.EditUserProfileUseCase
import javax.inject.Inject

class  StartRegistrationFragmentVM @Inject constructor(
    val editUserProfileUseCase: EditUserProfileUseCase,
    val downloadUserProfileUseCase: DownloadUserProfileUseCase
): ViewModel() {

    private val mutableState = MutableStateFlow<User?>(null)
    val state: StateFlow<User?> get() = mutableState


}