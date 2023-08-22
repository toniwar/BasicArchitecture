package ru.otus.basicarchitecture.presentation.date_manage

import android.app.DatePickerDialog
import android.content.Context
import ru.otus.basicarchitecture.domain.repositories.DatePicker
import java.util.Calendar

object DatePickerDialog: DatePicker {
    private val calendar = Calendar.getInstance()
    private val d = calendar.get(Calendar.DAY_OF_MONTH)
    private val m = calendar.get(Calendar.MONTH)
    private val y = calendar.get(Calendar.YEAR)
    lateinit var dpd: DatePickerDialog


    override fun showDatePickerDialog(context: Context, dateCallBack: (String)->Unit){

        dpd = DatePickerDialog(context,DatePickerDialog.OnDateSetListener{_, year, month, day ->
            dateCallBack.invoke("$day.${month+1}.$year")
        }, y, m, d)
        dpd.show()

    }

}
