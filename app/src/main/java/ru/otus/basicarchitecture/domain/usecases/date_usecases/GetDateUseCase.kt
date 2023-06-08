package ru.otus.basicarchitecture.domain.usecases.date_usecases

import ru.otus.basicarchitecture.domain.repository.DatePicker

class GetDateUseCase(private val datePicker: DatePicker) {
    fun getDate():String{
        return datePicker.getDate()
    }
}