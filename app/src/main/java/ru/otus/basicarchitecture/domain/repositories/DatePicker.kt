package ru.otus.basicarchitecture.domain.repositories

import android.content.Context


interface DatePicker {

    fun showDatePickerDialog(context: Context, dateCallBack: (String)->Unit)

}