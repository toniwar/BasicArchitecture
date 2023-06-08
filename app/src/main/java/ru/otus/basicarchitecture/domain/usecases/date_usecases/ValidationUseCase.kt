package ru.otus.basicarchitecture.domain.usecases.date_usecases

import ru.otus.basicarchitecture.domain.repository.DatePicker

class ValidationUseCase(private val datePicker: DatePicker) {

    fun validation():Boolean{
        return datePicker.validation()
    }
}