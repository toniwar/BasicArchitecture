package ru.otus.basicarchitecture.domain.use_cases

import javax.inject.Inject

class InputDataCheckerUseCase @Inject constructor() {
    fun checkInputData(inputData: Collection<String?>): Boolean{
        inputData.forEach {
            if(it.isNullOrBlank()) return false
        }
        return true
    }
}