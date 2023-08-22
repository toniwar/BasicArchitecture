package ru.otus.basicarchitecture.presentation.date_manage

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.NumberFormatException
import java.util.Calendar
import kotlin.math.abs

object DateChecker {
    private val dateHolder = DateHolder()
    private fun getCurrentYear(): Int{
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.YEAR)
    }
    private val currentYear = getCurrentYear()
    private const val minYear = 1900
    private const val adult = 18
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


    fun ageValidator(inp: String?): Boolean{
        if(inp == null) return false
        val year = numberFormatChecker(inp)
        if(year == -1) return false
        val checkedYear = numRange(year, minYear..currentYear)
        if(checkedYear == -1) return false
        return currentYear - checkedYear >= adult

    }

    enum class DateParams{
        DD, MM, YYYY
    }
}
