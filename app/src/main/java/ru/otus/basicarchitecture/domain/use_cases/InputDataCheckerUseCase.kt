package ru.otus.basicarchitecture.domain.use_cases

import javax.inject.Inject

class InputDataCheckerUseCase @Inject constructor() {
    fun checkInputData(inputData: List<String?>): Boolean{
        inputData.forEach {
            if(it.isNullOrBlank()) return false
        }
        return true
    }
}