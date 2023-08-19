package ru.otus.basicarchitecture.presentation.custom_views

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.otus.basicarchitecture.R
import java.lang.NumberFormatException
import java.util.Calendar
import kotlin.math.abs

class DateEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
): androidx.appcompat.widget.AppCompatEditText(context, attributeSet) {
    private var dateParam = 0
    init {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.DateEditText)
        dateParam = typedArray.getInt(R.styleable.DateEditText_customIntegerValue, 0)
        hint = when(dateParam){
            0 -> "DD"
            1 -> "MM"
            2 -> "YYYY"
            else -> ""
        }
        typedArray.recycle()
    }
    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if(!focused){
            when(dateParam){
                0 ->{
                    DateChecker.verifyDate(text.toString(), DateChecker.DateParams.DD)
                }
                1 ->{
                    DateChecker.verifyDate(text.toString(), DateChecker.DateParams.MM)
                }
                2 ->{
                    DateChecker.verifyDate(text.toString(), DateChecker.DateParams.YYYY)
                }
            }
        }
    }
    companion object {
        data class DateHolder(
            var day: Int = -1,
            var month: Int = -1,
            var year: Int = -1
        )
        object DateChecker {
            private val dateHolder = DateHolder()
            private fun getCurrentYear(): Int{
                val calendar = Calendar.getInstance()
                return calendar.get(Calendar.YEAR)
            }
            private val currentYear = getCurrentYear()
            private const val minYear = 1930
            private val mutableDateFlow = MutableStateFlow<DateHolder?>(null)
            val dateFlow: StateFlow<DateHolder?>
                get() = mutableDateFlow

            fun verifyDate(input:String?, dateParam: DateParams)
            {
                when(dateParam){
                    DateParams.DD -> {
                        dateHolder.day = numberFormatChecker(input?:"")
                    }
                    DateParams.MM ->{
                        dateHolder.month = numberFormatChecker(input?:"")
                    }
                    DateParams.YYYY ->{
                        dateHolder.year = numberFormatChecker(input?:"")
                    }
                }
                dateHolderChecker()
                mutableDateFlow.value = null
                mutableDateFlow.value = dateHolder
            }
            private fun numberFormatChecker(inp: String): Int{
                return try {
                    abs(inp.toInt())
                }catch (e: NumberFormatException){-1}
            }
            private fun yearChecker(inp: Int): Boolean{
                if(inp % 400 == 0) return true
                if(inp % 100 == 0) return false
                return inp % 4 == 0
            }
            private fun dateHolderChecker(){
                val checkedYear = numRange(dateHolder.year, minYear..currentYear)
                dateHolder.year = checkedYear
                val checkedMonth = numRange(dateHolder.month, 1..12)
                dateHolder.month = checkedMonth
                val maxRangeForDay = when(dateHolder.month){
                    -1 -> 31
                    4,6,9,11 -> 30
                    2-> {
                        if(dateHolder.year < 0) 29 else{
                            if(yearChecker(dateHolder.year)) 29 else 28
                        }
                    }
                    else -> 31
                }
                val checkedDay = numRange(dateHolder.day, 1..maxRangeForDay)
                dateHolder.day = checkedDay
            }
            private fun numRange(inp: Int, range: IntRange): Int{
                return if(inp in range) inp else -1
            }
            enum class DateParams{
                DD, MM, YYYY
            }
        }
    }
}
