package com.example.todoapp.custom

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatTextView
import com.example.todoapp.R

class TaskTitleView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init(){

    }
}