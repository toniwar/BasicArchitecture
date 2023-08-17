package ru.otus.basicarchitecture.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.NumberFormatException
import java.util.Calendar
import kotlin.math.abs

object DateChecker {

    private fun getCurrentYear(): Int{
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.YEAR)
    }

    private val currentYear = getCurrentYear()
    private const val minYear = 1930
    private val mutableDateMap = MutableStateFlow<Map<DateContent, String>?>(null)
    val dateMap: StateFlow<Map<DateContent, String>?>get() = mutableDateMap

    fun verifyDate(
        day: String?,
        month: String?,
        year: String?
                   )
    {
        val dateMap = mutableMapOf<DateContent, String>()


        val checkedYear = numberFormatChecker(year?:"", minYear..currentYear)
        dateMap[DateContent.YYYY] = if(checkedYear == -1)"" else checkedYear.toString()

        val checkedMonth = numberFormatChecker(month?:"", 1..12)
        dateMap[DateContent.MM] = if(checkedMonth == -1)"" else checkedMonth.toString()

        val maxRangeForDay = when(checkedMonth){
            -1 -> 31
            4,6,9,11 -> 30
            2-> {
                if(checkedYear < 0) 29 else{
                    if(yearChecker(checkedYear)) 29 else 28
                }
            }
            else -> 31

        }
        val checkedDay = numberFormatChecker(day?:"", 1..maxRangeForDay)
        dateMap[DateContent.DD] = if(checkedDay == -1)"" else checkedDay.toString()

        mutableDateMap.value = dateMap.toMap()

    }

    private fun numberFormatChecker(inp: String, range: IntRange): Int{
        val res = try {
            abs(inp.toInt())
        }catch (e: NumberFormatException){-1}

        return if(res in range) res else -1

    }
    private fun yearChecker(inp: Int): Boolean{
        if(inp % 400 == 0) return true
        if(inp % 100 == 0) return false
        return inp % 4 == 0
    }
    enum class DateContent{
        DD, MM, YYYY
    }
}