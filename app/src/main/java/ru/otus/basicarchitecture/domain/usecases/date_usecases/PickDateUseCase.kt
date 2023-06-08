package ru.otus.basicarchitecture.domain.usecases.date_usecases

import android.content.Context
import android.widget.TextView
import ru.otus.basicarchitecture.domain.repository.DatePicker

class PickDateUseCase(private val datePicker: DatePicker) {

    fun pickDate(context: Context, textView: TextView){
        datePicker.showDatePickerDialog(context, textView)
    }
}