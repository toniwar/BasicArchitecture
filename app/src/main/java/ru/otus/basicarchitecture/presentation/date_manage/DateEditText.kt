package ru.otus.basicarchitecture.presentation.date_manage

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import ru.otus.basicarchitecture.R


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

}
