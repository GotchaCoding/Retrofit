package org.techtown.retrofit2.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import org.techtown.retrofit2.R

class MyButton : AppCompatButton {
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("MyButton", "onDraw 호출됨")
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        setBackgroundColor(Color.BLUE)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
//        return super.onTouchEvent(event);
        Log.d("MyButton", "onTouchEvent 호출됨")
        val action = event.action
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                setBackgroundColor(Color.BLUE)
                setTextColor(Color.RED)
            }
            MotionEvent.ACTION_OUTSIDE, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.CYAN)
                setTextColor(Color.BLACK)
            }
        }
        invalidate()
        return true
    }

    private fun init(context: Context) {
        setBackgroundColor(Color.CYAN)
        setTextColor(Color.BLACK)
        val textSize = resources.getDimension(R.dimen.text_size)
        setTextSize(textSize)
    }
}
